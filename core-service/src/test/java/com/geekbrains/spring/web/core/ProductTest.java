package com.geekbrains.spring.web.core;

import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.core.entities.Product;
import com.geekbrains.spring.web.core.repositories.ProductsRepository;
import com.geekbrains.spring.web.core.services.ProductsService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
@RequiredArgsConstructor
class ProductTest {

	@Autowired
	private ProductsService productsService;

	@Mock
	private ProductsRepository productsRepository;

	@Test
	public void findByIdTest(){
		Long id = 5L;
		Product product = new Product();
		product.setId(id);
		product.setPrice(BigDecimal.valueOf(20.00));
		product.setTitle("Кукуруза");

		Mockito.doReturn(Optional.of(product)).when(productsRepository).findById(id);
		Assertions.assertEquals("Кукуруза", productsService.findById(id).get().getTitle());
	}
}