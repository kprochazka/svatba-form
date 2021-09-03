package kprochazka.wedding.form;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.HEAD;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@CrossOrigin(origins = "*", methods = {HEAD, GET, PUT, POST, PATCH, DELETE})
@RestController
public class HelloEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(HelloEndpoint.class);

    @GetMapping("hello")
    public ResponseEntity<Object> hello(HttpServletRequest request) {
        logger.info("Hello World!");
        return ResponseEntity.ok("Hello World!");
    }

}
