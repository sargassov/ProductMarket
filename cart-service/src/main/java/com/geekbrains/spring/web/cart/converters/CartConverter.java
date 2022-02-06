package com.geekbrains.spring.web.cart.converters;

import com.geekbrains.spring.web.api.cart.CartDto;
import com.geekbrains.spring.web.api.cart.CartItemDto;
import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.cart.models.Cart;
import com.geekbrains.spring.web.cart.models.CartItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartConverter {

    public CartDto modelToDto(Cart cart) {
        List<CartItemDto> cartItemDtos = cart.getItems().stream().map(c ->
                new CartItemDto(c.getProductId(), c.getProductTitle(), c.getQuantity(), c.getPricePerProduct(), c.getPrice()))
                .collect(Collectors.toList());

        return new CartDto(cartItemDtos, cart.getTotalPrice());

    }
}
