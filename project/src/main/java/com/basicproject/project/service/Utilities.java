package com.basicproject.project.service;

import com.basicproject.project.entities.Userdto;
import com.basicproject.project.repository.UserdtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Utilities {

    @Autowired
    UserdtoRepository userdtoRepository;
    public Userdto validateUser(String userId) {
        return userdtoRepository.findById(Long.valueOf(userId)).orElse(null);
    }
}
