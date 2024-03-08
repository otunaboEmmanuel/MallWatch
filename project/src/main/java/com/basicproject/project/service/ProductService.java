package com.basicproject.project.service;

import com.basicproject.project.dto.ProductDto;
import com.basicproject.project.entities.Products;

import java.util.List;

public interface ProductService {
    Products addNewProduct(ProductDto products);

    List<Products> findAllProductsByStore(String storeName);

    Products findProductById(String productId);
}
