package com.italo.waiter.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Company extends AbstractEntity{
    private String name;

    @OneToMany(mappedBy = "company")
    private List<SystemUser> systemUsers = new ArrayList<>();
}
