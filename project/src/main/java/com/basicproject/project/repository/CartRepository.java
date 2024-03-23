package com.basicproject.project.repository;

import com.basicproject.project.entities.Cart;
import com.basicproject.project.entities.Userdto;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    public List<Cart> findByUser(Userdto user);
}
