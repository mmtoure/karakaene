package com.mmt.karakaene.service.validation;

import com.mmt.karakaene.model.User;
import com.mmt.karakaene.model.Validation;
import com.mmt.karakaene.repository.ValidationRepository;
import com.mmt.karakaene.service.notificationsService.NotificationEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService {

    private final ValidationRepository validationRepository;
    private final NotificationEmailService notificationEmailService;
    @Override
    public Validation createValidation(Validation validation) {
        return null;
    }

    @Override
    public void validationByUser(User user) {
        Validation validation = new Validation();
        Instant creation = Instant.now();
        validation.setCreation(creation);
        Instant expiration = creation.plus(10, ChronoUnit.MINUTES);
        validation.setExpiration(expiration);
        Random random = new Random();
        int randomInteger = random.nextInt(999999);
        String code = String.format("%06d",randomInteger);
        validation.setCode(code);
        validation.setUser(user);
        Validation newValidation = this.validationRepository.save(validation);
        this.notificationEmailService.sendEmail(validation);

    }

    @Override
    public void deleteValidation(Long id) {

    }
}
