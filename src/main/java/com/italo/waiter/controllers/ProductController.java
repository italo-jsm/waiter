package com.italo.waiter.controllers;

import com.italo.waiter.model.Product;
import com.italo.waiter.model.dto.ProductDto;
import com.italo.waiter.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    @Autowired @Lazy
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> findAllProducts(){
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findProductById(@PathVariable Long id){
        return productService.findProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto product ) throws URISyntaxException {
        return ResponseEntity.created(new URI("/products/" + productService.saveProduct(product.toProduct()).getId())).build();
    }
}
