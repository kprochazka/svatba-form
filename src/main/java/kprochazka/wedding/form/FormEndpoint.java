package kprochazka.wedding.form;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;

import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.HEAD;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@CrossOrigin(origins = "*", methods = {HEAD, GET, PUT, POST, PATCH, DELETE})
@RestController
public class FormEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(HelloEndpoint.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping(value = "form", consumes = APPLICATION_FORM_URLENCODED_VALUE, produces = TEXT_PLAIN_VALUE)
    public ResponseEntity mail(FormData data) {
        logger.info("Form endpoint called by {}!", data.name);
        sendEmail(data);
        return ResponseEntity.accepted().build();
    }

    private void sendEmail(FormData data) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("prochazkovi2021@no-reply.com");
        msg.setTo("prochazka.kamil@gmail.com", "luci.divisova@seznam.cz");
        msg.setSubject("Prochazkovi2021 Formular");
        msg.setText(printMailText(data));
        javaMailSender.send(msg);
    }

    private String printMailText(FormData data) {
        return format("Jméno: %s%n" +
                        "Email: %s%n" +
                        "Počet hostů: %d%n" +
                        "Nejoblíbenější cukroví: %s%n" +
                        "Písnička (ženich, nevěsta): %s%n" +
                        "Moje písnička: %s%n" +
                        "Poznámky: %n%s%n",
                data.name, data.email, data.guest, data.sweets, data.song, data.partySong, data.notes);
    }

    @Data
    @AllArgsConstructor
    static class FormData {
        private String name;
        private String email;
        private int guest;
        private String sweets;
        private String song;
        private String partySong;
        private String notes;
    }

}
