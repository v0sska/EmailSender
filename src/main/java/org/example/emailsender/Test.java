package org.example.emailsender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.emailsender.enums.Status;
import org.example.emailsender.repositories.MessagesRepository;
import org.example.emailsender.services.MessagesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/test")
public class Test {

    private MessagesRepository messagesRepository;
    private MessagesService messagesService;

    @GetMapping
    public ResponseEntity<Object> test(){
        return new ResponseEntity<>(messagesRepository.findAll(), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody Status status){
        messagesService.updateStatus(id, status);
        return new ResponseEntity<>(messagesRepository.findById(id), HttpStatus.OK);
    }

}
