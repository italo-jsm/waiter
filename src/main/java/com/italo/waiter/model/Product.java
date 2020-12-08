package com.italo.waiter.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Entity
public class Product extends AbstractEntity{
    private String code;
    private String description;
    private Double saleCost;
    private Double productionCost;
}
