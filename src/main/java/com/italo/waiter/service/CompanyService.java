package com.italo.waiter.service;

import com.italo.waiter.model.Company;
import com.italo.waiter.repository.CompanyRepository;
import com.italo.waiter.repository.SystemUserRepository;
import com.italo.waiter.utils.enums.ErrorMessage;
import com.italo.waiter.utils.exceptions.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    private final SystemUserRepository systemUserRepository;

    @Autowired @Lazy
    public CompanyService(CompanyRepository companyRepository, SystemUserRepository systemUserRepository) {
        this.companyRepository = companyRepository;
        this.systemUserRepository = systemUserRepository;
    }

    public Company saveCompany(Company company) {
        if(company.getSystemUsers().isEmpty()){
            throw new ConflictException(ErrorMessage.COMPANY_WITH_NO_USERS.getFormattedMessage());
        }
        company.getSystemUsers().forEach(systemUser -> {
            if(systemUserRepository.findByUsername(systemUser.getUsername()).isPresent())throw new ConflictException(ErrorMessage.COMPANY_SYSTEM_USER_EXISTS.getFormattedMessage());
        });
        return companyRepository.save(company);
    }
}
