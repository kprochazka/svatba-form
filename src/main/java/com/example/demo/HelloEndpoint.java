package com.example.demo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloEndpoint {

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("hello")
    public ResponseEntity<Object> hello(HttpServletRequest request) {
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("mail")
    public ResponseEntity mail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("prochazka.kamil@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

        return ResponseEntity.ok("sent");
    }

}
