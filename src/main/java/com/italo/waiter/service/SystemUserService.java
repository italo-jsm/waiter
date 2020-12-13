package com.italo.waiter.service;

import com.italo.waiter.model.SystemUser;
import com.italo.waiter.repository.SystemUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SystemUserService {

    private final SystemUserRepository systemUserRepository;

    public Optional<SystemUser> findUserById(Long id){
        return systemUserRepository.findById(id);
    }

    public SystemUser updateUser(SystemUser user){
        return systemUserRepository.findByUsername(user.getUsername())
                .map(systemUser -> {
                    systemUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
                    return systemUserRepository.save(systemUser);
                }).orElse(null);
    }

}
