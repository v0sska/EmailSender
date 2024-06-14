package org.example.emailsender.message_broker;

import lombok.AllArgsConstructor;
import org.example.emailsender.entites.MessagesToSend;
import org.example.emailsender.entites.Messages;
import org.example.emailsender.enums.Status;
import org.example.emailsender.repositories.MessagesRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Listener {

    private MessagesRepository messagesRepository;

    @KafkaListener(topics = "${kafka.topic.messageToEmail}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(MessagesToSend message) {

        Messages messageToSent = new Messages();

        messageToSent.setContent(message.getContent());
        messageToSent.setEmail(message.getEmail());
        messageToSent.setSubject(message.getSubject());
        messageToSent.setStatus(Status.RECEIVED);

        System.out.println("Received message: " + message);

        messagesRepository.save(messageToSent);
    }

}
