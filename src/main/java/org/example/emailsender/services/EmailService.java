package org.example.emailsender.services;

import lombok.AllArgsConstructor;
import org.example.emailsender.entites.Messages;
import org.example.emailsender.enums.Status;
import org.example.emailsender.interfaces.IEmailService;
import org.example.emailsender.message_broker.Publisher;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmailService implements IEmailService {

    private Publisher publisher;

    private MessagesService messagesService;

    private JavaMailSender mailSender;

    @Override
    public void sentEmail(String email, String subject, String content) {

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
                // Логувати виняток, якщо потрібно
                System.err.println("Error sending email to " + message.getEmail() + ": " + e.getMessage());
                messagesService.updateStatus(message.getId(), Status.ERROR);
            }
        }





    }

    @Override
    public void checkStatusAndSendEmail() {

    }
}
