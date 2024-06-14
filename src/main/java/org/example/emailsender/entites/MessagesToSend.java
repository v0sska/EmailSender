package org.example.emailsender.entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessagesToSend {
    private String id;

    private String content;

    private String subject;

    private String email;

    @Override
    public String toString() {
        return "KafkaMessages{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
