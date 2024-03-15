package com.basicproject.project.controller;


import com.basicproject.project.dto.LoginDto;
import com.basicproject.project.entities.ForgotPassword;
import com.basicproject.project.entities.Responses;
import com.basicproject.project.entities.Userdto;
import com.basicproject.project.repository.UserdtoRepository;
import com.basicproject.project.service.UserdtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/project")
@CrossOrigin
public class UserController {
    @Autowired
    private UserdtoService userdtoService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserdtoRepository userRepository;
    @PostMapping("/saveusers")
    public ResponseEntity<?> saveUsers(@RequestBody Userdto userdto) {
        Responses responses=new Responses();
        Userdto userResult = userdtoService.saveUsers(userdto);
        return (userResult == null)? new ResponseEntity<>(new Responses("111","email or username already in use"),HttpStatus.OK):new ResponseEntity<>(new Responses("00", "user saved successfully"), HttpStatus.OK);

    }
//    @PostMapping("/login")
//    public ResponseEntity<?>findByEmailAndPassword(@RequestBody Userdto userdto)
//    {
//       Userdto login=userRepository.findByEmailAndPassword(userdto.getEmail(), userdto.getPassword()).orElse(null);
//       if (login==null)
//       {
//           return new ResponseEntity<>(new Responses("00","Invalid email or password. Try again"),HttpStatus.OK);
//    }else {
//           return new ResponseEntity<>(new Responses("111","login successfully"),HttpStatus.OK);
//       }
//
//    }
    @PostMapping("/login")
    public ResponseEntity<?>LoginUser(@RequestBody LoginDto loginDto){
        Userdto login=userRepository.findByEmail(loginDto.getEmail()).orElse(null);
        if(login!=null){
            String password=loginDto.getPassword();
            String encodedPassword= login.getPassword();
            Boolean isPwdRight=passwordEncoder.matches(password,encodedPassword);
            if(isPwdRight){
                Optional<Userdto> user =userRepository.findByEmailAndPassword(loginDto.getEmail(), encodedPassword);
                if(user.isPresent())
                {
                    return new ResponseEntity<>(new Responses("00","login successfully"),HttpStatus.OK);
                }else {
                    return new ResponseEntity<>(new Responses("111","login failed"),HttpStatus.OK);
                }
            }else {
                return new ResponseEntity<>(new Responses("111","password doesn't match"),HttpStatus.OK);
            }
        }else {
            return new ResponseEntity<>(new Responses("111","Email doesn't exist"),HttpStatus.OK);
        }

    }

}
