package com.basicproject.project.controller;


import com.basicproject.project.dto.ProductDto;
import com.basicproject.project.dto.Responses.CustomResponse;
import com.basicproject.project.dto.Responses.Responses;
import com.basicproject.project.entities.Products;
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
    public ResponseEntity<?> addProducts(@RequestBody ProductDto products) {
        log.info("Incoming requests with body "+products.toString());

        Products result = productService.addNewProduct(products);
        return result != null ? new ResponseEntity<>(new CustomResponse(Responses.PRODUCT_CREATED.getCode(), Responses.PRODUCT_CREATED.getMessage()), HttpStatus.OK)
                : new ResponseEntity<>(new CustomResponse(Responses.UNEXPECTED_ERROR.getCode(), Responses.UNEXPECTED_ERROR.getMessage()), HttpStatus.OK);
    }

    @PostMapping("/findProductsByStore")
    public ResponseEntity<?> findProductsByStore(@RequestBody ProductDto productDto) {
        log.info("Incoming DTO is "+productDto);

        List<Products> allStoreProducts = productService.findAllProductsByStore(productDto.getStoreName());
        return new ResponseEntity<>(allStoreProducts, HttpStatus.OK);
    }

    @PostMapping("/findProductsyId")
    public ResponseEntity<?> findProductsById(@RequestBody ProductDto productDto) {
        log.info("Incoming DTO "+productDto);

        Products products = productService.findProductById(productDto.getProductId());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


}
