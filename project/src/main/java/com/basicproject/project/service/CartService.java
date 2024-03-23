package com.basicproject.project.service;

import com.basicproject.project.entities.Cart;
import com.basicproject.project.entities.Products;
import com.basicproject.project.repository.CartRepository;
import com.basicproject.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
     private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Utilities utils;

}
