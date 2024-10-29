package com.project.P1_Revshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Service
public class Email_Service {
	@Autowired
    private  JavaMailSender mailSender;
	public void CustomerPasswordChange(String name,String to, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("revshoptechm@gmail.com");
        message.setTo(to);
        message.setSubject("Hello "+name+", Trying to change password");
        message.setText("Your verification code for Changing the password : " + verificationCode+"\n");
        mailSender.send(message);
    }
	public void SellerPasswordChange(String name,String to, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("revshoptechm@gmail.com");
        message.setTo(to);
        message.setSubject("Hello "+name+", Trying to change password");
        message.setText("Your verification code for Changing the password : " + verificationCode+"\n");
        mailSender.send(message);
    }
	
	
	public void sendEmail(String to, String subject, String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true); // Use true if HTML content is included
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new IllegalStateException("Failed to send email", e);
        }
    }

}
