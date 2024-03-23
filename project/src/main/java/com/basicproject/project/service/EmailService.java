package com.basicproject.project.service;

import net.bytebuddy.asm.MemberSubstitution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleMessage(MailBody mailBody){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(mailBody.getTo());
        message.setFrom("support@unitsession.com");
        message.setSubject(mailBody.getSubject());
        message.setText(mailBody.getText());

        javaMailSender.send(message);

    }
}
