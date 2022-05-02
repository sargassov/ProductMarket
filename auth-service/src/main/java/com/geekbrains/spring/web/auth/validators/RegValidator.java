package com.geekbrains.spring.web.auth.validators;

import com.geekbrains.spring.web.auth.dto.JwtRequest;
import com.geekbrains.spring.web.auth.dto.RegRequest;
import com.geekbrains.spring.web.auth.exceptions.ValidationException;
import com.geekbrains.spring.web.auth.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class RegValidator {
    private final UserService userService;

    public void validate(RegRequest regRequest) {
        List<String> errors = new ArrayList<>();
        if (regRequest.getLogin().isBlank()) {
            errors.add("Отсутствует имя пользователя");
        }
        if (regRequest.getPassword().isBlank()) {
            errors.add("Отсутствует пароль");
        }
        if(!userService.validateLoginInDatabase(regRequest.getLogin())){
            errors.add("Логин не уникальный");
        }
        if (regRequest.getLogin().isBlank()) {
            errors.add("Отсутствует пароль");
        }
        if (regRequest.getEmail().isBlank()) {
            errors.add("Отсутствует элетронная почта");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
