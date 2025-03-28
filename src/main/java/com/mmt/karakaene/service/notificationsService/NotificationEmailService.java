package com.mmt.karakaene.service.notificationsService;

import com.mmt.karakaene.model.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationEmailService {
    private final JavaMailSender javaMailSender;

    public void sendEmail(Validation validation){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply@karakaene.com");
        message.setTo(validation.getUser().getEmail());
        message.setSubject("Code de validation");
        message.setText("Votre code de validation est: "+validation.getCode());
        javaMailSender.send(message);

    }
}
