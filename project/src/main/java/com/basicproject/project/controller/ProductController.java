package com.basicproject.project.controller;


import com.basicproject.project.dto.Emaildto;
import com.basicproject.project.dto.LoginDto;
import com.basicproject.project.dto.ProductDto;
import com.basicproject.project.dto.UserRoles;
import com.basicproject.project.entities.Products;
import com.basicproject.project.entities.Responses;
import com.basicproject.project.entities.Userdto;
import com.basicproject.project.repository.ProductRepository;
import com.basicproject.project.repository.UserdtoRepository;
import com.basicproject.project.service.ProductService;
import com.basicproject.project.service.Utilities;
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
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserdtoRepository userdtoRepository;

    @Autowired
    private Utilities utils;


    @PostMapping("/add-product")
    public ResponseEntity<?> addProducts(@RequestBody Products products) {
        log.info("Incoming requests with body "+products.toString());

        Products result = productService.addNewProduct(products);
        return result != null ? new ResponseEntity<>(new Responses("00", "Record Saved Successfully"), HttpStatus.OK)
                : new ResponseEntity<>(new Responses("99", "Record not saved, Ensure product name does not exist"), HttpStatus.OK);
    }

    @PostMapping("/findProductsByStore")
    public ResponseEntity<?> findProductsByStore(@RequestBody ProductDto productDto) {
        log.info("Incoming DTO is "+productDto);

        List<Products> allStoreProducts = productService.findAllProductsByStore(productDto.getStoreName());
        return new ResponseEntity<>(allStoreProducts, HttpStatus.OK);
    }
    @PostMapping("/delete-products")
    public String deleteProductsById(@RequestBody Products products)
    {
        productService.deleteProduct(products.getProductId());
        return"Products deleted Sucessfully";
    }
    @PostMapping("/allProducts")
    public ResponseEntity<?> getAllProducts(@RequestBody ProductDto productDto){
        List<Products> products=productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    @PostMapping("/allProducts-category")
    public ResponseEntity<?> getAllProductsByCategory(@RequestBody ProductDto productDto){
        List<Products> Category = productService.findAllProductsByCategoryName(productDto.getCategoryName());
        return new ResponseEntity<>(Category, HttpStatus.OK);
    }
    @PostMapping("/findProductsBySubCategory")
    public ResponseEntity<?> findProductsBySubCategory(@RequestBody ProductDto productDto) {

        List<Products> SubCategory = productService.findAllProductsBySubCategoryName(productDto.getSubCategoryName());
        return new ResponseEntity<>(SubCategory, HttpStatus.OK);
    }
    @PostMapping("/updateProducts")
    public ResponseEntity<?> UpdateProducts (@RequestBody ProductDto productDto) {
        Userdto user = utils.validateUser(productDto.getUserId());

        if (user == null || !user.getRoles().equals(UserRoles.ADMIN)) {
            return new ResponseEntity<>(new Responses("99", "Unauthorized user"), HttpStatus.OK);
        } else {
            Products products = productRepository.findById(productDto.getProductId()).orElse(null);
            if (products == null) {
                return new ResponseEntity<>(new Responses("99", "Product not found"), HttpStatus.OK);
            } else {
                products.setPrice(productDto.getPrice());
//                products.setProductImages(productDto.getProductImages());
                products.setProductName(productDto.getProductName());
                products.setDiscounted(productDto.getDiscounted());
                products.setPrice(productDto.getPrice());
                products.setAvailableUnits(productDto.getAvailableUnits());
                products.setStoreName(productDto.getStoreName());
                products.setCategoryName(productDto.getCategoryName());
                products.setDiscountedPrice(productDto.getDiscountedPrice());
                products.setSubCategoryName(productDto.getCategoryName());
                productRepository.save(products);
                return new ResponseEntity<>(new Responses("00", "Product updated"), HttpStatus.OK);
            }
        }
    }
    @PostMapping("/updateRole")
    public ResponseEntity<?> UpdateRole(@RequestBody LoginDto user)
    {
        Userdto userData = utils.validateUser(String.valueOf(user.getInitiatorUserId()));
        if(userData.getRoles().equals(UserRoles.SUPER_ADMIN))
        {
            Userdto user1 = utils.validateUser(String.valueOf(user.getUserId()));
            if(user1 != null) {
                user1.setRoles(UserRoles.ADMIN);
                user1.setStore(user.getStoreName());
               userdtoRepository.save(user1);
                return new ResponseEntity<>(new Responses("00", "Updated"), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(new Responses("99", "Unauthorized user"), HttpStatus.OK);
            }

        }else
        {
            return new ResponseEntity<>(new Responses("99", "Unauthorized user"), HttpStatus.OK);
        }

    }

}
