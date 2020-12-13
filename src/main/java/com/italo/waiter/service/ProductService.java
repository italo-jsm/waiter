package com.italo.waiter.service;

import com.italo.waiter.model.Product;
import com.italo.waiter.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> findProductById(Long id){
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> findProductsByCompany(String username) {
        return this.productRepository.findBySystemUserCompany(username);
    }
}
