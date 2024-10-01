package com.example.util;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;

@Component
public class EmailUtil {

    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}") private String sender;

    public EmailUtil(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String sendOtpEmail(String email, String otp) throws MessagingException {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(email);
            mailMessage.setText("Your code: " + otp);
            mailMessage.setSubject("Just title");

            javaMailSender.send(mailMessage);

            return "Mail Sent Successfully...";

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String errorDetails = sw.toString();

            return "Error while Sending Mail: " + errorDetails;
        }
    }
}