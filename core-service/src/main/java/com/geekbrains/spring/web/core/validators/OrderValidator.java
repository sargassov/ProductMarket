package com.geekbrains.spring.web.core.validators;

import com.geekbrains.spring.web.api.core.OrderDetailsDto;
import com.geekbrains.spring.web.api.core.OrderDto;
import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.core.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderValidator {
    private static ArrayList<String> errors = new ArrayList<>();
    public void validate(String username) {
        String error = "";
        if (username.length() < 1) {
            error = "Поданы пустые идентификационные данные";
        }
        isThrow();
    }

    public void validate(String username, OrderDetailsDto orderDetailsDto) {
        if (orderDetailsDto.getStreet().length() < 3) {
            errors.add("Неверно указана улица");
        }
        if (orderDetailsDto.getIndex().length() < 6) {
            errors.add("Неверно указан индекс");
        }
        if (orderDetailsDto.getPhone().length() < 11) {
            errors.add("Неверно указан телефон");
        }
        if (orderDetailsDto.getCity().length() < 3) {
            errors.add("Неверно указан город");
        }
        if (orderDetailsDto.getHouse().length() < 1) {
            errors.add("Неверно указана дом");
        }
        validate(username);
        isThrow();
    }

    public void validate(Long id) {
        if (id < 0) {
            errors.add("Подано отрицательное число в id");
        }
        isThrow();
    }

    private void isThrow(){
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
