package com.geekbrains.spring.web.cart.validators;

import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.cart.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class CartValidator {
    private List<String> errors = new ArrayList<>();

    public void validateString(String username) {
        if (username.isBlank()) {
            errors.add("UUID не может быть пустым");
        }
        isTrhow();
    }

    public void validateLong(Long id) {
        if (id < 0) {
            errors.add("ID корзины не может быть отрицательным");
        }
        isTrhow();
    }

    private void isTrhow(){
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
