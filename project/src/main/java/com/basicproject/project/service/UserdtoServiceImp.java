package com.basicproject.project.service;



import com.basicproject.project.entities.Userdto;
import com.basicproject.project.repository.UserdtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserdtoServiceImp implements UserdtoService {
    @Autowired
    private UserdtoRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Override
    public Userdto saveUsers(Userdto userdto) {
        Userdto user1 = userRepository.findByEmail(userdto.getEmail()).orElse(null);
        userdto.setPassword(bcryptEncoder.encode(userdto.getPassword()));
        return (user1==null )? userRepository.save(userdto):null;

    }
}
