package com.italo.waiter.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class RemoteConsumingUnit extends ConsumingUnit{
    @OneToOne
    private Address address;
    private String phone;
    private String name;
}
