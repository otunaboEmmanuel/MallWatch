package com.basicproject.project.controller;

import com.basicproject.project.dto.Emaildto;
import com.basicproject.project.entities.ForgotPassword;
import com.basicproject.project.entities.Responses;
import com.basicproject.project.entities.Userdto;
import com.basicproject.project.repository.ForgotPasswordRepository;
import com.basicproject.project.repository.UserdtoRepository;
import com.basicproject.project.service.EmailService;
import com.basicproject.project.service.MailBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/forgotPassword")
@Slf4j
public class ForgotPasswordController {

    @Autowired
    private UserdtoRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ForgotPasswordRepository forgotPasswordRepository;





    @GetMapping("/test")
    public String checkUrl() {
        return "Working::::";
    }
    @PostMapping("/verifyMail")
    public ResponseEntity<?> verifyEmail(@RequestBody Emaildto emaildto){
        Userdto userdto=userRepository.findByEmail(emaildto.getEmail()).orElse(null);

        if(userdto != null) {
            Integer otp = otpGenerator();
            MailBody mailBody = new MailBody(emaildto.getEmail(), "OTP for forgot password", "This is your otp " + otp);
            ForgotPassword fp=new ForgotPassword();
            fp.setOtp(otp);
            fp.setExpirationTime(new Date(System.currentTimeMillis() + 10 * 60 * 1000));
            fp.setUserdto(userdto);
            emailService.sendSimpleMessage(mailBody);
            forgotPasswordRepository.save(fp);
            return new ResponseEntity<>(new Responses("00","OTP HAS BEEN SENT"),HttpStatus.OK);
        }
        else {

            return new ResponseEntity<>(new Responses("111","Email doesn't exist"),HttpStatus.OK);
        }
    }
    @PostMapping("/otpVerification")
    public ResponseEntity<?> verifyOtp(@RequestBody Emaildto emaildto)
    {
        Userdto userdto=userRepository.findByEmail(emaildto.getEmail()).orElse(null);
        ForgotPassword fp=forgotPasswordRepository.findByOtpAnduserdto(emaildto.getOtp(),userdto).orElse(null);
        if(userdto==null||fp==null){
            return new ResponseEntity<>(new Responses("100","Invalid otp or wrong email"),HttpStatus.EXPECTATION_FAILED);
        }

        if (fp.getExpirationTime().before(Date.from(Instant.now())))
        {
            forgotPasswordRepository.deleteById(fp.getFpid());
            return new ResponseEntity<>(new Responses("100","OTP HAS EXPIRED"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new Responses("00","OTP VERIFIED"),HttpStatus.OK);
    }
    @PostMapping("/changePassword")
    public ResponseEntity<?> changePasswordHandler( @RequestBody Emaildto emaildto )
    {
        if (!Objects.equals(emaildto.getPassword(), emaildto.getRepeatPassword())) {

            return new ResponseEntity<>(new Responses("111","wrong credentials.Rewrite your username and password"),HttpStatus.EXPECTATION_FAILED);
        } else {
            Userdto user = userRepository.findByEmail(emaildto.getEmail()).orElse(null);
            if(user==null){
                return new ResponseEntity<>(new Responses("111","Invalid email"),HttpStatus.BAD_REQUEST);
            }

            user.setPassword(passwordEncoder.encode(emaildto.getPassword()));
            userRepository.save(user);
            return new ResponseEntity<>(new Responses("00","password saved successfully"),HttpStatus.OK);
        }
    }
    private Integer otpGenerator(){
        //return UUID.randomUUID().toString().replace("-", "").substring(0,6);
        Random random=new Random();
        return random.nextInt(100000);
    }
}
