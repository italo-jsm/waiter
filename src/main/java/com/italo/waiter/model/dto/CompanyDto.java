package com.italo.waiter.model.dto;

import com.italo.waiter.model.SystemUser;

import java.util.ArrayList;
import java.util.List;

public class CompanyDto {
    private String name;
    private List<SystemUserDto> systemUsers = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SystemUserDto> getSystemUsers() {
        return systemUsers;
    }

    public void setSystemUsers(List<SystemUserDto> systemUsers) {
        this.systemUsers = systemUsers;
    }
}
