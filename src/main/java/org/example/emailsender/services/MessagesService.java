package org.example.emailsender.services;

import lombok.AllArgsConstructor;
import org.example.emailsender.entites.Messages;
import org.example.emailsender.enums.Status;
import org.example.emailsender.interfaces.IMessagesService;
import org.example.emailsender.repositories.MessagesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessagesService implements IMessagesService {

  private MessagesRepository messagesRepository;

    public void addMessages(String content, String email, String subject) {
        Messages message = new Messages();
        message.setContent(content);
        message.setEmail(email);
        message.setSubject(subject);
        message.setStatus(Status.RECEIVED);
        messagesRepository.save(message);
    }

    public void updateStatus(String id, Status status) {
    Messages message = messagesRepository.findById(id).orElse(null);
    if (message != null) {
        message.setStatus(status);
        messagesRepository.save(message);
    } else {
        System.out.println("Message with id " + id + " not found");
    }
}

    public List<Messages> findMessagesByStatus(String status) {
        return messagesRepository.findByStatus(status);
    }

}
