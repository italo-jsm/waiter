package com.italo.waiter.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.italo.waiter.utils.enums.UnitStatus;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public SystemUser getOpenedBy() {
        return openedBy;
    }

    public void setOpenedBy(SystemUser openedBy) {
        this.openedBy = openedBy;
    }

    public UnitStatus getStatus() {
        return status;
    }

    public void setStatus(UnitStatus status) {
        this.status = status;
    }

    public SystemUser getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(SystemUser closedBy) {
        this.closedBy = closedBy;
    }

    @Override
    public String toString() {
        return "ConsumingUnit{" +
                "id=" + this.getId() +
                "number=" + number +
                ", peoples=" + peoples +
               // ", commandItems=" + commandItems +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsumingUnit unit = (ConsumingUnit) o;
        return Objects.equals(number, unit.number) &&
                Objects.equals(peoples, unit.peoples) &&
                Objects.equals(openedBy, unit.openedBy) &&
                Objects.equals(closedBy, unit.closedBy) &&
                status == unit.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, peoples, commandItems, openedBy, closedBy, status);
    }

    public Double getTotalBill() {
        return this.getCommandItems()
                .stream()
                .map(commandItem -> commandItem.getQuantity() * commandItem.getProduct().getSaleCost())
                .reduce(Double::sum)
                .orElseThrow(() -> new RuntimeException("Impossible Calc"));
    }
}
