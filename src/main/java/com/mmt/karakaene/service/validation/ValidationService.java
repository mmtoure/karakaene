package com.mmt.karakaene.service.validation;

import com.mmt.karakaene.model.User;
import com.mmt.karakaene.model.Validation;

import java.util.Map;


public interface ValidationService {
    Validation createValidation(Validation validation);
    void validationByUser(User user);
    void deleteValidation(Long id);
    Validation getValidationByCode(String code);
}
