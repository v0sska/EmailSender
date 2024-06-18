package org.example.emailsender.message_broker;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.example.emailsender.entites.MessagesToSend;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class MessagesInitializer {

    public List<MessagesToSend> firstMessages = new ArrayList<>();

    @PostConstruct
    public void initMessages(){

        firstMessages.add(new MessagesToSend("1", "Content 1", "Subject 1", "emailtotest@example.com"));
        firstMessages.add(new MessagesToSend("2", "Content 2", "Subject 2", "emailtotest@example.com"));
        firstMessages.add(new MessagesToSend("3", "Content 3", "Subject 3", "emailtotest@example.com"));

    }
}
