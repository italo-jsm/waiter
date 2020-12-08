package com.italo.waiter.model.dto;

import com.italo.waiter.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String code;
    private String description;
    private Double saleCost;
    private Double productionCost;

    public Product toProduct() {
        Product product = new Product();
        product.setCode(code);
        product.setDescription(description);
        product.setProductionCost(productionCost);
        product.setSaleCost(saleCost);
        return product;
    }
}
