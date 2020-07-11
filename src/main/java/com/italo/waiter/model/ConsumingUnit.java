package com.italo.waiter.model;

import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ConsumingUnit extends AbstractEntity{
    private Integer number;
    private Integer peoples;
    @OneToMany(mappedBy = "consumingUnit", fetch = FetchType.LAZY)
    private List<CommandItem> commandItems = new ArrayList<>();

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getPeoples() {
        return peoples;
    }

    public void setPeoples(Integer peoples) {
        this.peoples = peoples;
    }

    public List<CommandItem> getCommandItems() {
        return commandItems;
    }

    public void setCommandItems(List<CommandItem> commandItems) {
        this.commandItems = commandItems;
    }

    @Override
    public String toString() {
        return "ConsumingUnit{" +
                "id=" + id +
                "number=" + number +
                ", peoples=" + peoples +
               // ", commandItems=" + commandItems +
                '}';
    }

    public ConsumingUnit loadCommandItens(){
        Hibernate.initialize(commandItems);
        return this;
    }
}
