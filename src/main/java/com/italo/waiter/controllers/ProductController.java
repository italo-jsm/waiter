package com.italo.waiter.controllers;

import com.italo.waiter.model.Product;
import com.italo.waiter.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired @Lazy
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findAllProducts(){
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("{id}") @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findProductById(@PathVariable Long id){
        return productService.findProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> saveProduct(@RequestBody Product product ) throws URISyntaxException {
        return ResponseEntity.created(new URI("/products/" + productService.saveProduct(product).getId())).build();
    }
}
