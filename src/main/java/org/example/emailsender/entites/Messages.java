package org.example.emailsender.entites;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@NoArgsConstructor
public class Messages {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Text)
    private String subject;

    @Field(type = FieldType.Text)
    private String email;

    public Messages(String content, String subject, String email) {
        this.content = content;
        this.subject = subject;
        this.email = email;
    }
}
