package com.helmi.TunningMarket.services;

import com.helmi.TunningMarket.entities.Cart;
import com.helmi.TunningMarket.entities.Email;
import com.helmi.TunningMarket.entities.Produit;
import com.helmi.TunningMarket.entities.User;
import com.helmi.TunningMarket.repositories.EmailRepository;
import com.helmi.TunningMarket.repositories.UserRepository;
import com.helmi.TunningMarket.requests.CartRequest;
import com.helmi.TunningMarket.requests.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    EmailRepository emailRepository;
    @Autowired
    UserRepository userRepository;
    @Async
    public void sendEmail(SimpleMailMessage email) {
        mailSender.send(email);
    }

    public Email saveEmail (EmailRequest emailRequest){

     Email email = new Email();
     email.setBody(emailRequest.getBody());
     email.setMailTo(emailRequest.getMailTo());
     email.setSubject(emailRequest.getSubject());
     sendSimpleEmail(emailRequest.getMailTo(),emailRequest.getBody(),emailRequest.getSubject());
     return  emailRepository.save(email);
    }

    public void sendSimpleEmail(String toEmail,
                                String body,
                                String subject) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("helmi.bali@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
        System.out.println("Mail Send...");
    }

}
