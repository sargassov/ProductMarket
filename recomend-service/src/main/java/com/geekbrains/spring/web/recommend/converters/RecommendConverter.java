package com.geekbrains.spring.web.recommend.converters;

import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.api.recommend.RecommendDto;
import com.geekbrains.spring.web.recommend.entites.Recommend;

public class RecommendConverter {
    public Recommend dtoToEntity(RecommendDto recommendDto) {
        return new Recommend(recommendDto.getId(), recommendDto.getProductId(), recommendDto.getQuantity());
    }

    public RecommendDto entityToDto(Recommend recommend) {
        return new RecommendDto(recommend.getId(), recommend.getProductId(), recommend.getQuantity());
    }
}
