package org.example.emailsender.message_broker;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.example.emailsender.entites.MessagesToSend;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Publisher {

    private static final String TOPIC = "messageToEmail";

    private MessagesInitializer messagesInitializer;

    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(MessagesToSend message) {
        kafkaTemplate.send(TOPIC, message);
        System.out.println("Sent message: " + message);
    }


    @PostConstruct
    public void sendInitialMessage() {
        for(MessagesToSend m : messagesInitializer.getFirstMessages()){
            kafkaTemplate.send(TOPIC, m);
            System.out.println("Sent message: " + m);
        }
    }
}
