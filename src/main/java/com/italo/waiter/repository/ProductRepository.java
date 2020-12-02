package com.italo.waiter.repository;

import com.italo.waiter.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select * from product where created_by_username = any (" +
            "select username from system_user where company_id = any (" +
            "select company_id from system_user where username = :username ))", nativeQuery = true)
    public List<Product> findBySystemUserCompany(String username);
}
