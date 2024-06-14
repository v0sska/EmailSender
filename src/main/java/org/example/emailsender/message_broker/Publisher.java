package org.example.emailsender.message_broker;

import lombok.AllArgsConstructor;
import org.example.emailsender.entites.MessagesToSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Publisher {

    private static final String TOPIC = "messageToEmail";

    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(MessagesToSend message) {
        kafkaTemplate.send(TOPIC, message);
        System.out.println("Sent message: " + message);
    }

}
