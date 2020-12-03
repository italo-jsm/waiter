package com.italo.waiter.service.builder;

import com.italo.waiter.model.Company;
import com.italo.waiter.model.SystemUser;

public class SystemUserTestBuilder {
    public static SystemUser generateSystemUser(){
        SystemUser s1 = new SystemUser();
        s1.setUsername("username");
        s1.setPassword("password");
        return s1;
    }
}
