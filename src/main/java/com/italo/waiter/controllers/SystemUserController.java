package com.italo.waiter.controllers;

import com.italo.waiter.model.SystemUser;
import com.italo.waiter.repository.SystemUserRepository;
import com.italo.waiter.service.SystemUserService;
import com.italo.waiter.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/system-user")
public class SystemUserController {

    private final SystemUserRepository systemUserRepository;
    private final SystemUserService systemUserService;

    @GetMapping
    public ResponseEntity<?> findAllUsers(){
        return ResponseEntity.ok(systemUserRepository.findAll());
    }

    @GetMapping("{id}") @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findUserById(@PathVariable Long id){
        return systemUserService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("Usuario nao encontrado"));
    }

    @PostMapping @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> saveUser(@RequestBody SystemUser user){
        return systemUserRepository.findByUsername(user.getUsername())
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

    @PutMapping @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateUser(@RequestBody SystemUser user){
        return Optional.ofNullable(systemUserService.updateUser(user))
                .map(systemUser -> ResponseEntity.ok().build())
                .orElse(ResponseEntity.notFound().build());
    }
}
