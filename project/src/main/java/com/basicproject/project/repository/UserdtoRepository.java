package com.basicproject.project.repository;


import com.basicproject.project.entities.Userdto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserdtoRepository extends JpaRepository<Userdto,Long> {
    Optional<Userdto> findByEmail (String email);




}
