package com.italo.waiter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
