package com.basicproject.project.controller;


import com.basicproject.project.entities.Responses;
import com.basicproject.project.entities.Userdto;
import com.basicproject.project.service.UserdtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@CrossOrigin
public class UserController {
    @Autowired
    private UserdtoService userdtoService;
    @PostMapping("/saveusers")
    public ResponseEntity<?> saveUsers(@RequestBody Userdto userdto) {
        Responses responses=new Responses();
        Userdto userResult = userdtoService.saveUsers(userdto);
        return (userResult == null)? new ResponseEntity<>(new Responses("100","email or username already in use"),HttpStatus.OK):new ResponseEntity<>(new Responses("00", "user saved successfully"), HttpStatus.OK);

    }

}
