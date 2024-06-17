package org.example.emailsender.interfaces;

import org.example.emailsender.entites.Messages;
import org.example.emailsender.enums.Status;

import java.util.List;

public interface IMessagesService {

    void addMessages(String content, String email, String subject);

    void updateStatus(String id, Status status);

    List<Messages> findMessagesByStatus(String status);

}
