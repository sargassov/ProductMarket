package com.geekbrains.spring.web.auth.validators;

import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.auth.dto.JwtRequest;
import com.geekbrains.spring.web.auth.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthValidator {
    public void validate(JwtRequest authRequest) {
        List<String> errors = new ArrayList<>();
        if (authRequest.getUsername().isBlank()) {
            errors.add("Отсутствует имя пользователя");
        }
        if (authRequest.getPassword().isBlank()) {
            errors.add("Отсутствует пароль");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
