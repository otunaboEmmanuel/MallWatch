package com.basicproject.project.repository;

import com.basicproject.project.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    Optional<Products> findByProductName(String productName);

    List<Products> findByStoreName(String storeName);
}
