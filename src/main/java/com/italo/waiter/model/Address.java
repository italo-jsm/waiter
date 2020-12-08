package com.italo.waiter.model;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Address extends AbstractEntity{
    private String street;
    private String neighborhood;
    private String postalCode;
    private String number;
    private String city;
    private String state;
}
