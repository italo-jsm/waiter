package com.italo.waiter.service;

import com.italo.waiter.model.Company;
import com.italo.waiter.repository.CompanyRepository;
import com.italo.waiter.repository.RoleRepository;
import com.italo.waiter.repository.SystemUserRepository;
import com.italo.waiter.utils.enums.ErrorMessage;
import com.italo.waiter.utils.exceptions.ConflictException;
import com.italo.waiter.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    private final SystemUserRepository systemUserRepository;

    private final RoleRepository roleRepository;

    public Company saveCompany(Company company) {
        if(company.getSystemUsers().isEmpty()){
            throw new ConflictException(ErrorMessage.COMPANY_WITH_NO_USERS.getFormattedMessage());
        }
        company.getSystemUsers().forEach(systemUser -> {
            if(systemUserRepository.findByUsername(systemUser.getUsername()).isPresent()){
                throw new ConflictException(ErrorMessage.COMPANY_SYSTEM_USER_EXISTS.getFormattedMessage());
            }
        });
        companyRepository.save(company);
        company.getSystemUsers().forEach(systemUser -> {
            systemUser.setPassword(new BCryptPasswordEncoder().encode(systemUser.getPassword()));
            systemUser.setCompany(company);
            systemUser.setRoles(
                    systemUser.getRoles()
                    .stream()
                    .map(role -> roleRepository.findByName(role.getName()).orElseThrow(() -> new NotFoundException(ErrorMessage.ROLE_NOT_FOUND.getFormattedMessage()))).collect(Collectors.toList())
            );
            systemUserRepository.save(systemUser);
        });
        return companyRepository.save(company);
    }
}
