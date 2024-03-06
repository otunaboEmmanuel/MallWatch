package com.basicproject.project.controller;


import com.basicproject.project.dto.ProductDto;
import com.basicproject.project.entities.Products;
import com.basicproject.project.entities.Responses;
import com.basicproject.project.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/add-product")
    public ResponseEntity<?> addProducts(@RequestBody Products products) {
        log.info("Incoming requests with body "+products.toString());

        Products result = productService.addNewProduct(products);
        return products != null ? new ResponseEntity<>(new Responses("00", "Record Saved Successfully"), HttpStatus.OK)
                : new ResponseEntity<>(new Responses("99", "Record not saved, Ensure product name does not exist"), HttpStatus.OK);
    }

    @PostMapping("/findProductsByStore")
    public ResponseEntity<?> findProductsByStore(@RequestBody ProductDto productDto) {
        log.info("Incoming DTO is "+productDto);

        List<Products> allStoreProducts = productService.findAllProductsByStore(productDto.getStoreName());
        return new ResponseEntity<>(allStoreProducts, HttpStatus.OK);
    }
}
