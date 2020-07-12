package com.italo.waiter.model;


import com.italo.waiter.utils.enums.UnitStatus;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

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
    private UnitStatus status;

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
                "id=" + id +
                "number=" + number +
                ", peoples=" + peoples +
               // ", commandItems=" + commandItems +
                '}';
    }

}
