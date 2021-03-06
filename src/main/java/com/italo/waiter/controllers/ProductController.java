package com.italo.waiter.controllers;

import com.italo.waiter.model.Company;
import com.italo.waiter.model.Product;
import com.italo.waiter.model.dto.ProductDto;
import com.italo.waiter.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;
    private final Logger logger = LoggerFactory.getLogger("ProductController");

    @GetMapping @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findAllProducts(Principal principal){
        logger.info("Getting all products");
        return ResponseEntity.ok(productService.findProductsByCompany(principal.getName()));
    }

    @GetMapping("{id}") @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findProductById(@PathVariable Long id){
        return productService.findProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto product) throws URISyntaxException {
        return ResponseEntity.created(new URI("/products/" + productService.saveProduct(product.toProduct()).getId())).build();
    }
}
