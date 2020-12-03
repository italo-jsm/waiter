package com.italo.waiter.service.builder;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.italo.waiter.model.Company;
import com.italo.waiter.model.SystemUser;

public class CompanyServiceTestBuilder {
    public static Company generateCompany(){
        Company c = new Company();
        c.setName("Company 1");
        return c;
    }
}
