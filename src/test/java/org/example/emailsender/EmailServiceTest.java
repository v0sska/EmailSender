package org.example.emailsender;

import org.example.emailsender.entites.Messages;
import org.example.emailsender.enums.Status;
import org.example.emailsender.interfaces.IMessagesService;
import org.example.emailsender.services.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class EmailServiceTest {

    @Mock
    private IMessagesService messagesService;

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSentEmail_SuccessfulSend() {
        // Given
        List<Messages> messages = new ArrayList<>();
        Messages message = new Messages();
        message.setId("432dfnlfalj");
        message.setEmail("test@example.com");
        message.setSubject("Test Subject");
        message.setContent("Test Content");
        message.setStatus(Status.RECEIVED);
        messages.add(message);

        when(messagesService.findMessagesByStatus(Status.RECEIVED.name())).thenReturn(messages);

        // When
        emailService.sentEmail();

        // Then
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
        verify(messagesService, times(1)).updateStatus("432dfnlfalj", Status.SENDED);
    }

    @Test
    public void testCheckStatusAndSendEmail_RetryOnError() {
        // Given
        List<Messages> errorMessages = new ArrayList<>();
        Messages errorMessage = new Messages();
        errorMessage.setId("hgjhrgsfdLNJBGUOAFNI13");
        errorMessage.setEmail("error@example.com");
        errorMessage.setSubject("Error Subject");
        errorMessage.setContent("Error Content");
        errorMessage.setStatus(Status.ERROR);
        errorMessages.add(errorMessage);

        when(messagesService.findMessagesByStatus(Status.ERROR.name())).thenReturn(errorMessages);

        doThrow(new RuntimeException("Simulated send error")).when(mailSender).send(any(SimpleMailMessage.class));

        // When
        emailService.checkStatusAndSendEmail();

        // Then
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
        verify(messagesService, times(1)).updateStatus("hgjhrgsfdLNJBGUOAFNI13", Status.ERROR);
        verify(messagesService, never()).updateStatus("hgjhrgsfdLNJBGUOAFNI13", Status.SENDED); // should not mark as SENT
    }
}

