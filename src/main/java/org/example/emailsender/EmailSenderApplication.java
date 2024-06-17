package org.example.emailsender;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.example.emailsender.interfaces.IEmailService;
import org.example.emailsender.services.EmailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableScheduling
public class EmailSenderApplication {


    public static void main(String[] args) {
        SpringApplication.run(EmailSenderApplication.class, args);
    }
}
