package com.ecommerce.backend.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to,String subject,String body){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
    public void sendWelcomeEmail(String to){
        sendEmail(to,"Welcome to App","Your Account created successfully");
    }
    public void sendOrderEmail(String to,Long orderId){
        sendEmail(to,
                "Order placed successfully ",
                "Your order id"+orderId
        );
    }
    public void sendPaymentEmail(String to,Double amount){
        sendEmail(to,"Payment successfull ","Payment of "+amount+"received successfull ");
    }
}
