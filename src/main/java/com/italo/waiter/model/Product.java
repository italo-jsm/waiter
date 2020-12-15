package com.italo.waiter.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Product extends AbstractEntity{
    private String code;
    private String description;
    private Double saleCost;
    private Double productionCost;
}
