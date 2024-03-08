package com.basicproject.project.service;

import com.basicproject.project.dto.UsersDto;
import com.basicproject.project.entities.Roles;
import com.basicproject.project.entities.Users;
import com.basicproject.project.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> users = userRepository.findByEmailAndActive(email, true);

        if(users.isPresent()) {
            return users.get();
        }
        else {
            throw new UsernameNotFoundException("User not found with Email: "+email);
        }


    }

    public Users createNewUser(UsersDto userDto) {

        Optional<Users> user = userRepository.findByEmail(userDto.getEmail());
        if(user.isPresent()) {
            log.info("Email already exists on the DB");
            throw new RuntimeException("User already exists");
        }

        Users users = new Users();
        users.setFirstName(userDto.getFirstName());
        users.setLastName(userDto.getLastName());
        users.setRole(Roles.USER);
        users.setEmail(userDto.getEmail());
        users.setPassword(passwordEncoder.encode(userDto.getPassword()));
        users.setFullName(userDto.getFirstName()+" "+userDto.getLastName());
        users.setProfilePicture(userDto.getProfilePicture());
        users.setActive(true);

        try{
            userRepository.save(users);
        }
        catch(Exception e) {
            throw new RuntimeException("Email already exists");
        }

        return users;
    }
}
