package com.italo.waiter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Entity
public class CommandItem extends AbstractEntity{
    private Integer quantity;
    @OneToOne
    private Product product;
    @OneToOne @JsonIgnore
    private ConsumingUnit consumingUnit;
    @OneToOne
    private SystemUser registeredBy;
}
