package com.italo.waiter.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Product extends AbstractEntity{
    private String code;
    private String description;
    private Double saleCost;
    private Double productionCost;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSaleCost() {
        return saleCost;
    }

    public void setSaleCost(Double saleCost) {
        this.saleCost = saleCost;
    }

    public Double getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(Double productionCost) {
        this.productionCost = productionCost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + this.getId() +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", saleCost=" + saleCost +
                ", productionCost=" + productionCost +
                '}';
    }
}
