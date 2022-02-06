package com.geekbrains.spring.web;

import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.cart.integrations.ProductServiceIntegration;
import com.geekbrains.spring.web.cart.models.Cart;
import com.geekbrains.spring.web.cart.models.CartItem;
import com.geekbrains.spring.web.cart.services.CartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = CartService.class)
public class CartTest {

    @Autowired
    private CartService cartService;

    @MockBean
    private ProductServiceIntegration productServiceIntegration;;

    @BeforeEach
    public void initCart() {
        cartService.clearCart("test_cart");
    }

    @Test
    public void addToCartTest() {

        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setTitle("Product");
        productDto.setPrice(67);


        Mockito.doReturn(Optional.of(productDto)).when(productServiceIntegration).findById(1L);
        cartService.addToCart("test_cart", 1L);
        cartService.addToCart("test_cart", 1L);
        cartService.addToCart("test_cart", 1L);

        Mockito.verify(productServiceIntegration, Mockito.times(1)).findById(ArgumentMatchers.eq(1L));
        Assertions.assertEquals(1, cartService.getCurrentCart("test_cart").getItems().size());
    }
}
