package org.example.emailsender.services;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.example.emailsender.entites.Messages;
import org.example.emailsender.enums.Status;
import org.example.emailsender.interfaces.IEmailService;
import org.example.emailsender.message_broker.Publisher;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
@Log4j
public class EmailService implements IEmailService {

    private Publisher publisher;

    private MessagesService messagesService;

    private JavaMailSender mailSender;

    private final ConcurrentHashMap<String, Integer> retryAttempts = new ConcurrentHashMap<>();


    @Override
    public void sentEmail() {

        List<Messages> messagesToSent = messagesService.findMessagesByStatus(Status.RECEIVED);

        for (Messages message : messagesToSent) {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            try {
                mailMessage.setTo(message.getEmail());
                mailMessage.setSubject(message.getSubject());
                mailMessage.setText(message.getContent());
                mailSender.send(mailMessage);
                messagesService.updateStatus(message.getId(), Status.SENDED);
            } catch (Exception e) {
                System.err.println("Error sending email to " + message.getEmail() + ": " + e.getMessage());
                messagesService.updateStatus(message.getId(), Status.ERROR);
            }
        }
    }

    @Override
    @Scheduled(fixedRate = 300000) // 5 хвилин
    public void checkStatusAndSendEmail() {

        List<Messages> errorMessages = messagesService.findMessagesByStatus(Status.ERROR);

        for (Messages message : errorMessages) {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            try {
                mailMessage.setTo(message.getEmail());
                mailMessage.setSubject(message.getSubject());
                mailMessage.setText(message.getContent());
                mailSender.send(mailMessage);
                messagesService.updateStatus(message.getId(), Status.SENDED);
                retryAttempts.remove(message.getId()); // скидання спроб після успішної відправки
            } catch (Exception e) {
                System.err.println("Error sending email to " + message.getEmail() + ": " + e.getMessage());
                retryAttempts.merge(message.getId(), 1, Integer::sum); // збільшення кількості спроб
                messagesService.updateStatus(message.getId(), Status.ERROR);
            }
        }
    }
}
