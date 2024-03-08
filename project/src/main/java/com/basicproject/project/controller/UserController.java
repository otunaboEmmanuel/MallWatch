package com.basicproject.project.controller;

import com.basicproject.project.dto.Responses.CustomResponse;
import com.basicproject.project.dto.Responses.Responses;
import com.basicproject.project.dto.UsersDto;
import com.basicproject.project.entities.Users;
import com.basicproject.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UsersDto userDto, CustomResponse customResponse) {

        log.info("Payload is "+userDto.toString());
        try {

            Users users = userService.createNewUser(userDto);
            customResponse = new CustomResponse(Responses.USER_CREATED.getCode(), Responses.USER_CREATED.getMessage());
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
        }
        catch(Exception e) {
            customResponse = new CustomResponse(Responses.UNEXPECTED_ERROR.getCode(), e.getMessage());
            return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> validateUser(@RequestBody UsersDto usersDto, CustomResponse customResponse) {

        log.info("payload is "+usersDto.toString());
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    usersDto.getEmail(), usersDto.getPassword()
            ));
            log.info("Successfully logged in::::");
            UserDetails userDetails = userService.loadUserByUsername(usersDto.getEmail());
            return new ResponseEntity<>(userDetails, HttpStatus.OK);
        }
        catch(BadCredentialsException e) {
            log.info("Failed to log in::::");
            customResponse = new CustomResponse(Responses.WRONG_CREDENTIALS.getCode(), Responses.WRONG_CREDENTIALS.getMessage());
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
        }
    }


}
