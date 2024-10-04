package com.project.P1_Revshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Forgot_Service {
	@Autowired
    private  JavaMailSender mailSender;
    // Send plain text email without MimeMessage
    public void sendVerificationEmail(String to, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("revshoptechm@gmail.com");
        message.setTo(to);
        message.setSubject("Password Reset Verification Code");
        message.setText("Your verification code is: " + verificationCode);
        mailSender.send(message);
    }
}
