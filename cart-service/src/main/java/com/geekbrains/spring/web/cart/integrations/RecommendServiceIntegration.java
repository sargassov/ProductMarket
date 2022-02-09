package com.geekbrains.spring.web.cart.integrations;

import com.geekbrains.spring.web.api.core.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RecommendServiceIntegration {
    private RestTemplate restTemplate;

    @Value("${integrations.recomend-service.url}")
    private String recomendServiceUrl;

    public void addById(Long id){
        restTemplate.getForObject(recomendServiceUrl + "/api/v1/recommends/" + id, Long.class);
    }
}
