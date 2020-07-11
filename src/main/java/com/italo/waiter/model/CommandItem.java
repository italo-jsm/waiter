package com.italo.waiter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class CommandItem extends AbstractEntity{
    private Integer quantity;
    @OneToOne
    private Product product;
    @OneToOne @JsonIgnore
    private ConsumingUnit consumingUnit;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ConsumingUnit getConsumingUnit() {
        return consumingUnit;
    }

    public void setConsumingUnit(ConsumingUnit consumingUnit) {
        this.consumingUnit = consumingUnit;
    }

    @Override
    public String toString() {
        return "CommandItem{" +
                "id=" + id +
                "quantity=" + quantity +
                ", product=" + product +
                '}';
    }
}
