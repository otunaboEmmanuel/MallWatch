package com.basicproject.project.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;
    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "productId")
    private Products product;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "userId")
    private Userdto user;

    public Cart() {
    }

    public Cart(Products product, Userdto user) {
        this.product = product;
        this.user = user;
    }
}
