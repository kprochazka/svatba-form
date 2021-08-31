package com.example.demo;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(HelloEndpoint.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment environment;

    @GetMapping("hello")
    public ResponseEntity<Object> hello(HttpServletRequest request) {
        logger.info("Hello World!");
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("mail")
    public ResponseEntity mail() {
        logger.info("Mail endpoint called!");

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("prochazka.kamil@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

        return ResponseEntity.ok("sent");
    }

}
