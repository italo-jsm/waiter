package com.italo.waiter.model.dto;

import com.italo.waiter.model.ConsumingUnit;

public class ConsumingUnitDto {
    private Integer number;
    private Integer people;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public ConsumingUnit toConsumingUnit(){
        ConsumingUnit unit = new ConsumingUnit();
        unit.setPeoples(this.people);
        unit.setNumber(this.number);
        return unit;
    }
}
