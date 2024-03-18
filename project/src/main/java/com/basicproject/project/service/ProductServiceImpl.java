package com.basicproject.project.service;

import com.basicproject.project.entities.Products;
import com.basicproject.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Override
    public Products addNewProduct(Products products) {

        Products products1 = productRepository.findByProductName(products.getProductName()).orElse(null);
        return products1 != null ? productRepository.save(products) : null;
    }

    @Override
    public List<Products> findAllProductsByStore(String storeName) {
        return productRepository.findByStoreName(storeName);
    }

    @Override
    public void deleteProduct(Long productId) {
         productRepository.deleteById(productId);
    }

    @Override
    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Products> findAllProductsByCategory(String categoryName) {
        return productRepository.findByCategory(categoryName);
    }

    @Override
    public List<Products> findAllProductsBySubCategory(String subCategoryName) {
        return productRepository.findBySubCategory(subCategoryName);
    }


}
