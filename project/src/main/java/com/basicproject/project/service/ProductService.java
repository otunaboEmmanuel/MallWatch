package com.basicproject.project.service;

import com.basicproject.project.entities.Products;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Products addNewProduct(Products products);

    List<Products> findAllProductsByStore(String storeName);

    void deleteProduct(Long productId);

    List<Products> getAllProducts();


    List<Products> findAllProductsByCategory(String categoryName);

    List<Products> findAllProductsBySubCategory(String subCategoryName);
}
