package com.basicproject.project.service;

import com.basicproject.project.dto.ProductDto;
import com.basicproject.project.entities.Products;
import com.basicproject.project.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Override
    public Products addNewProduct(ProductDto products) {

        Products products1 = productRepository.findByProductName(products.getProductName()).orElse(null);

        log.info("Products from DB is "+products1);

        Products products2 = new Products();
        products2.setProductImages(products.getProductImages());
        products2.setProductName(products.getProductName());
        products2.setPrice(products.getPrice());
        products2.setDiscounted(products.getDiscounted());
        products2.setCategoryName(products.getCategoryName());
        products2.setSubCategoryName(products.getSubCategoryName());
        products2.setStoreName(products.getStoreName());
        products2.setDiscounted(products.getDiscounted());
        products2.setAvailableUnits(products.getAvailableUnits());

        Products savedProducts = productRepository.save(products2);
        return savedProducts != null ? savedProducts : null;

    }

    @Override
    public List<Products> findAllProductsByStore(String storeName) {
        return productRepository.findByStoreName(storeName);
    }

    @Override
    public Products findProductById(String productId) {
        return productRepository.findByProductId(Long.valueOf(productId)).orElse(null);
    }
}
