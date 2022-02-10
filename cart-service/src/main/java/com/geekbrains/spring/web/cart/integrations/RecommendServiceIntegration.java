package com.geekbrains.spring.web.cart.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RecommendServiceIntegration {
    private RestTemplate restTemplate;

    @Value("${integrations.recommend-service.url}")
    private String recomendServiceUrl;

    public void addById(Long id){
        restTemplate.getForObject(recomendServiceUrl + "/api/v1/recommends/" + id, Long.class);
    }
}
