package com.italo.waiter.model.dto;

import com.italo.waiter.model.ConsumingUnit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsumingUnitDto {
    private Integer number;
    private Integer people;

    public ConsumingUnit toConsumingUnit(){
        ConsumingUnit unit = new ConsumingUnit();
        unit.setPeoples(this.people);
        unit.setNumber(this.number);
        return unit;
    }
}
