package com.italo.waiter.model.dto;

import com.italo.waiter.model.Product;

public class ProductDto {
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

    public Product toProduct() {
        Product product = new Product();
        product.setCode(code);
        product.setDescription(description);
        product.setProductionCost(productionCost);
        product.setSaleCost(saleCost);
        return product;
    }
}
