package com.geekbrains.spring.web;

import com.geekbrains.spring.web.core.entities.Product;
import com.geekbrains.spring.web.core.repositories.ProductsRepository;
import com.geekbrains.spring.web.core.services.ProductsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = ProductsService.class)
public class ProductServiceTest {

    @Autowired
    private ProductsService productService;

    @MockBean
    private ProductsRepository productsRepositoryTest;

    @Test
    public void findById() {
        Product product = new Product(1l, "food", 788);
        Product product2 = new Product(2l, "clothes", 2456);

        Mockito.doReturn(product).when(productsRepositoryTest).save(product);
        Mockito.doReturn(product2).when(productsRepositoryTest).save(product2);
        Mockito.doReturn(Optional.of(product)).when(productsRepositoryTest).findById(1L);
        Mockito.doReturn(Optional.of(product2)).when(productsRepositoryTest).findById(2L);

        Assertions.assertEquals(product, productsRepositoryTest.save(product));
        Assertions.assertNotEquals(product2, productsRepositoryTest.save(product));
        Assertions.assertNotEquals(product2, productsRepositoryTest.findById(1L));
    }
}
