package com.italo.waiter.model;

import lombok.*;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
