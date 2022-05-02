package com.geekbrains.spring.web.cart.test;

import com.geekbrains.spring.web.api.carts.CartDto;
import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.api.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.cart.integrations.ProductsServiceIntegration;
import com.geekbrains.spring.web.cart.models.Cart;
import com.geekbrains.spring.web.cart.services.CartService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.keyvalue.core.KeyValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RequiredArgsConstructor
public class CartTest {
    @Autowired
    private CartService cartService;
    private final String cartKey = "test_cart";
    private final Long id = 5L;
    private ProductDto productDto;
    private final String productTitle = "Кукуруза";
    private final double productCost = 50.00;

    @MockBean
    private ProductsServiceIntegration integration;

    @BeforeEach
    public void initCart(){
        cartService.clearCart(cartKey);
        productDto.setId(id);
        productDto.setPrice(BigDecimal.valueOf(productCost));
        productDto.setTitle(productTitle);
    }

    @Test
    public void verifyAddToCatTest(){
        Mockito.doReturn(Optional.of(productDto)).when(integration).findById(id);
        cartService.addToCart(cartKey, id);
        cartService.addToCart(cartKey, id);
        cartService.addToCart(cartKey, id);
        cartService.addToCart(cartKey, id);
        Mockito.verify(integration, Mockito.times(1)).findById(ArgumentMatchers.eq(id));
    }

    @Test
    public void verifySizeOfCartItemTest(){
        Mockito.doReturn(Optional.of(productDto)).when(integration).findById(id);
        cartService.addToCart(cartKey, id);
        cartService.addToCart(cartKey, id);
        cartService.addToCart(cartKey, id);
        cartService.addToCart(cartKey, id);
        Assertions.assertEquals(1, cartService.getCurrentCart(cartKey).getItems().size());
    }

    @Test
    public void verifyTotalCostOfCartTest(){
        Mockito.doReturn(Optional.of(productDto)).when(integration).findById(id);
        cartService.addToCart(cartKey, id);
        cartService.addToCart(cartKey, id);
        cartService.addToCart(cartKey, id);
        cartService.addToCart(cartKey, id);
        Assertions.assertEquals(BigDecimal.valueOf(200.00), cartService.getCurrentCart(cartKey).getTotalPrice());
    }

    @Test
    public void verifyProductTitleInTheCart(){
        Mockito.doReturn(Optional.of(productDto)).when(integration).findById(id);
        cartService.addToCart(cartKey, id);
        cartService.addToCart(cartKey, id);
        cartService.addToCart(cartKey, id);
        cartService.addToCart(cartKey, id);
        Assertions.assertEquals(productDto.getTitle(), cartService.getCurrentCart(cartKey).getItems().get(0).getProductTitle());
    }
}
