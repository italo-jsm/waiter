package com.italo.waiter.model.dto;

import java.util.ArrayList;
import java.util.List;

public class SystemUserDto {

    private String username;
    private String password;
    private List<RoleDto> roles = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }

}
