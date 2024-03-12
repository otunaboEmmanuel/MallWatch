package com.basicproject.project.controller;

import com.basicproject.project.dto.ChangePassword;
import com.basicproject.project.dto.emaildto;
import com.basicproject.project.entities.ForgotPassword;
import com.basicproject.project.entities.User;
import com.basicproject.project.repository.ForgotPasswordRepository;
import com.basicproject.project.repository.UserRepository;
import com.basicproject.project.service.EmailService;
import com.basicproject.project.service.MailBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/forgotPassword")
@Slf4j
public class ForgotPasswordController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ForgotPasswordRepository forgotPasswordRepository;

//    public ForgotPasswordController(UserRepository userRepository, EmailService emailService, PasswordEncoder passwordEncoder, ForgotPasswordRepository forgotPasswordRepository) {
//        this.userRepository = userRepository;
//        this.emailService = emailService;
//        this.passwordEncoder = passwordEncoder;
//        this.forgotPasswordRepository = forgotPasswordRepository;
//    }

    //send mail for email verification

    @GetMapping("/test")
    public String checkUrl() {
        return "Working::::";
    }
    @PostMapping("/verifyMail")
    public ResponseEntity<String> verifyEmail(@RequestBody emaildto emaildto){
        log.info("I dey here");
//        User user=userRepository.findByEmail(emaildto.getEmail()).orElseThrow(()-> new UsernameNotFoundException("please provide a valid email"));
        User user=userRepository.findByEmail(emaildto.getEmail()).orElse(null);

        if(user != null) {
            String otp = otpGenerator();
            MailBody mailBody = new MailBody(emaildto.getEmail(), "OTP for forgot password", "This is your otp" + otp);
            ForgotPassword fp = new ForgotPassword();
            fp.setOtp(otp);
            fp.setExpirationTime(new Date(System.currentTimeMillis() + 10 * 60 * 1000));
            fp.setUser(user);
            emailService.sendSimpleMessage(mailBody);
            forgotPasswordRepository.save(fp);
            return ResponseEntity.ok("Email sent for verification");
        }
        else {
            return ResponseEntity.ok("Email doesn't exist");
        }
    }
    @PostMapping("/otp-verification")
    public ResponseEntity<String> verifyOtp(@RequestBody emaildto emaildto)
    {
        User user=userRepository.findByEmail(emaildto.getEmail()).orElseThrow(()-> new UsernameNotFoundException("please provide a valid email"));
       ForgotPassword fp= forgotPasswordRepository.findByOtpAndUser(emaildto.getOtp(), user).orElseThrow(()-> new RuntimeException("Invalid otp for email"+emaildto.getEmail()));
       if(fp.getExpirationTime().before(Date.from(Instant.now())))
       {
           forgotPasswordRepository.deleteById(fp.getUser().getId());
         return new ResponseEntity<>("OTP has expired", HttpStatus.EXPECTATION_FAILED);
       }
         return ResponseEntity.ok("OTP verified");
    }
    @PostMapping("/changePassword")
    public ResponseEntity<String> changePasswordHandler(@RequestBody ChangePassword changePassword, @RequestBody emaildto emaildto )
    {
            if (!Objects.equals(changePassword.getPassword(),changePassword.getRepeatPassword())){
                return new ResponseEntity<>("Please enter your password again",HttpStatus.EXPECTATION_FAILED);
            }

        User user=userRepository.findByEmail(emaildto.getEmail()).orElseThrow(()-> new UsernameNotFoundException("please provide a valid email"));

            user.setPassword(passwordEncoder.encode(changePassword.getPassword()));
            userRepository.save(user);
//        String encodedPassword= passwordEncoder.encode(changePassword.getPassword());

//            userRepository.updatePassword(email,encodedPassword);
            return ResponseEntity.ok("Password has been changed");
    }
    private String otpGenerator(){
        return UUID.randomUUID().toString().replace("-", "").substring(0,6);
//        Random random=new Random();
//        return random.nextInt(100000,999999);
    }
}
