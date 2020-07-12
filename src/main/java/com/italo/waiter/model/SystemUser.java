package com.italo.waiter.model;

import javax.persistence.Entity;

@Entity
public class SystemUser {
    private String username;
    private String password;
    private String registrationNumber;

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

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String toString() {
        return "SystemUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                '}';
    }
}