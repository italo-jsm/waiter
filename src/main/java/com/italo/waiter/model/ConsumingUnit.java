package com.italo.waiter.model;


import com.italo.waiter.utils.enums.UnitStatus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class ConsumingUnit extends AbstractEntity{
    private Integer number;
    private Integer peoples;
    @OneToMany(mappedBy = "consumingUnit", fetch = FetchType.LAZY)
    private List<CommandItem> commandItems = new ArrayList<>();
    @OneToOne
    private SystemUser openedBy;
    @OneToOne
    private SystemUser closedBy;
    private UnitStatus status = UnitStatus.OPENED;

    public Double getTotalBill() {
        return this.getCommandItems()
                .stream()
                .map(commandItem -> commandItem.getQuantity() * commandItem.getProduct().getSaleCost())
                .reduce(Double::sum)
                .orElse(0.0);
    }
}
