package com.basicproject.project.service;


import com.basicproject.project.entities.User;
import com.basicproject.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Override
    public User saveUsers(User user) {
        User user1 = userRepository.findByEmail(user.getEmail()).orElse(null);
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        user.setRole("USER");
        return (user1==null )? userRepository.save(user):null;

    }
}
