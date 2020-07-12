package com.italo.waiter.controllers;

import com.italo.waiter.model.SystemUser;
import com.italo.waiter.repository.SystemUserRepository;
import com.italo.waiter.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/system-user")
public class SystemUserController {

    private final SystemUserRepository systemUserRepository;
    private final SystemUserService systemUserService;

    @Autowired @Lazy
    public SystemUserController(SystemUserRepository systemUserRepository, SystemUserService systemUserService) {
        this.systemUserRepository = systemUserRepository;
        this.systemUserService = systemUserService;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findProductById(@PathVariable Long id){
        return systemUserService.findProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody SystemUser user){
        return Optional.ofNullable(systemUserRepository.findByUsername(user.getUsername()))
                .map(systemUser -> ResponseEntity.badRequest().build())
                .orElseGet(() -> {
                    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
                    systemUserRepository.save(user);
                    try {
                        return ResponseEntity.created(new URI("/system-user/" + Long.toString(user.getId()))).build();
                    } catch (URISyntaxException e) {
                        return null;
                    }
                });
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody SystemUser user){
        return Optional.ofNullable(systemUserService.updateUser(user))
                .map(systemUser -> ResponseEntity.ok().build())
                .orElse(ResponseEntity.notFound().build());
    }
}
