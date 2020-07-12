package com.italo.waiter.service;

import com.italo.waiter.model.Product;
import com.italo.waiter.model.SystemUser;
import com.italo.waiter.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SystemUserService {

    private SystemUserRepository systemUserRepository;

    @Autowired @Lazy
    public SystemUserService(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }

    public Optional<SystemUser> findProductById(Long id){
        return systemUserRepository.findById(id);
    }


}
