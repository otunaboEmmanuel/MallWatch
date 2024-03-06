package com.basicproject.project.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String productId;

    @Column(name = "store_name")
    private String storeName;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "sub_category_name")
    private String subCategoryName;
    @Column(name = "product_name", unique = true)
    private String productName;
    @Column(name = "price")
    private double price;
    @Column(name = "discounted")
    private String discounted = "no";
    @Column(name = "discounted_price")
    private double discountedPrice;

    @Column(name = "available_units")
    private int availableUnits;


    @Lob
    @ElementCollection
    @Type(type="org.hibernate.type.TextType")
    @Column(name = "product_images", columnDefinition = "TEXT")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<String> productImages;
}
