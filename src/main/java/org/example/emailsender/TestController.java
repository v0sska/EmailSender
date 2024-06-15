package org.example.emailsender;

import org.example.emailsender.entites.MessagesToSend;
import org.example.emailsender.message_broker.Publisher;
import org.example.emailsender.repositories.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {


    @Autowired
    private Publisher messageProducer;

    @Autowired
    private MessagesRepository repository;

    @PostMapping
    public ResponseEntity<Object> sendMassage(@RequestBody MessagesToSend message) {
        messageProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent to Kafka topic");
    }

    @GetMapping
    public ResponseEntity<Object> getAllMessages() {
        return ResponseEntity.ok(repository.findAll());
    }
}
