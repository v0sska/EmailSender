package org.example.emailsender.interfaces;

public interface IEmailService {

    void sentEmail(String email, String subject, String content);

    void checkStatusAndSendEmail();

}
