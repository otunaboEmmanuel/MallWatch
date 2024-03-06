package com.basicproject.project.service;

import com.basicproject.project.entities.Products;

import java.util.List;

public interface ProductService {
    Products addNewProduct(Products products);

    List<Products> findAllProductsByStore(String storeName);
}
