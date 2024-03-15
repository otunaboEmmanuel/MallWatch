package com.basicproject.project.repository;


import com.basicproject.project.entities.ForgotPassword;
import com.basicproject.project.entities.Userdto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserdtoRepository extends JpaRepository<Userdto,Long> {
    Optional<Userdto> findByEmail (String email);

    Optional<Userdto> findByPassword (String password);


   Optional <Userdto> findByEmailAndPassword(String email, String Password);
}
