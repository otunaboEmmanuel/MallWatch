package com.basicproject.project.controller;

import com.basicproject.project.entities.User;
import com.basicproject.project.entities.Responses;
import com.basicproject.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")

public class ProjectController {
    @Autowired
    private UserService userService;
    @PostMapping("/saveusers")
    public ResponseEntity<?> saveUsers(@RequestBody User user) {
        Responses responses=new Responses();
        User userResult = userService.saveUsers(user);
        return (userResult == null)? new ResponseEntity<>(new Responses("100","email or username already in use"),HttpStatus.OK):new ResponseEntity<>(new Responses("00", "user saved successfully"), HttpStatus.OK);

    }

}
