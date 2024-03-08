package com.basicproject.project.dto;

import com.basicproject.project.entities.Products;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Products productDto;
    private String userId;
    private String color;
    private String size;
    private String quantity;

}
