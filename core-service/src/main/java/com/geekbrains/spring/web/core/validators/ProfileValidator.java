package com.geekbrains.spring.web.core.validators;

import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.core.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProfileValidator {
    public void validate(String username) {
        List<String> errors = new ArrayList<>();
        if (username.isBlank()) {
            errors.add("Поданы пустые данные по пользователю");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
