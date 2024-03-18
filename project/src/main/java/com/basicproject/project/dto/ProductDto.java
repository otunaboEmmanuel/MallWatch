package com.basicproject.project.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {


    private String storeName;
    private String categoryName;
    private String subCategoryName;
    private String productName;
    private double price;
    private String discounted;
    private double discountedPrice;
    private int availableUnits;
    private String userId;
    private Long ProductId;
    private Long ProductImages;
}
