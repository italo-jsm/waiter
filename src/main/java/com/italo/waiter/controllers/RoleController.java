package com.italo.waiter.controllers;

import com.italo.waiter.model.Role;
import com.italo.waiter.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/roles")
@CrossOrigin("*")
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles(){
        return ResponseEntity.ok(roleService.getAllRoles());
    }
}
