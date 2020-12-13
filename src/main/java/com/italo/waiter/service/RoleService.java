package com.italo.waiter.service;

import com.italo.waiter.model.Role;
import com.italo.waiter.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }
}
