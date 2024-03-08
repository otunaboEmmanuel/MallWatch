package com.basicproject.project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Carts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private Products product;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private Users users;

    private String size;
    private String color;
    private int quantity;
}
