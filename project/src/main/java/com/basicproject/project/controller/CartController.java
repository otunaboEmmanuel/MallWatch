package com.basicproject.project.controller;

import com.basicproject.project.dto.LoginDto;
import com.basicproject.project.dto.UserRoles;
import com.basicproject.project.entities.Cart;
import com.basicproject.project.entities.Products;
import com.basicproject.project.entities.Responses;
import com.basicproject.project.entities.Userdto;
import com.basicproject.project.repository.CartRepository;
import com.basicproject.project.repository.ProductRepository;
import com.basicproject.project.repository.UserdtoRepository;
import com.basicproject.project.service.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserdtoRepository userdtoRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Utilities utils;
    @PostMapping("/addCart")
    public ResponseEntity <?> addCart(@RequestBody LoginDto user) {
        Userdto user1 = utils.validateUser(String.valueOf(user.getUserId()));
        if (user1.getRoles().equals(UserRoles.USER)) {
            Products product = productRepository.findById(Long.valueOf(user.getProuctId())).orElse(null);
            if (product == null) {
                return new ResponseEntity<>(new Responses("00", "could not find product "), HttpStatus.OK);
            }

            Cart cart = new Cart(product, user1);
            cartRepository.save(cart);
            return new ResponseEntity<>(new Responses("00", "successfully added to cart "), HttpStatus.OK);

        }else
        {
            return new ResponseEntity<>(new Responses("99", "Unauthorized user"), HttpStatus.OK);
        }
    }
    @PostMapping("/getCartDetails")
    public List<Cart> getCartDetails(@RequestBody LoginDto user){
        Userdto user1 = utils.validateUser(String.valueOf(user.getUserId()));
        if (user1!=null)
        {
            Userdto result=userdtoRepository.findById(Long.valueOf(user.getUserId())).get();
            return cartRepository.findByUser(result);
        }
        return null;
    }
    @DeleteMapping("/deleteCartById")
    public String deleteCartById(@RequestBody LoginDto user)
    {
        cartRepository.deleteById(Long.valueOf(user.getCartId()));
        return "Cart Successfully deleted";
    }
   
}
