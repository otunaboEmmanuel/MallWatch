package com.basicproject.project.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {


    private String productId;
    private String storeName;
    private String categoryName;
    private String subCategoryName;
    private String productName;
    private double price;
    private String discounted;
    private double discountedPrice;
    private int availableUnits;
    private List<String> productImages;
}
