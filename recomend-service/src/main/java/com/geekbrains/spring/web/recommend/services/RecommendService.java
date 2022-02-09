package com.geekbrains.spring.web.recommend.services;

import com.geekbrains.spring.web.api.recommend.RecommendDto;
import com.geekbrains.spring.web.recommend.entites.Recommend;
import com.geekbrains.spring.web.recommend.repositories.RecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendService {
    private RecommendRepository recommendRepository;

    public List<RecommendDto> getAllRecommend() {
        List<RecommendDto> recommendDtos = recommendRepository.findAll().stream()
                .map(rec -> new RecommendDto(rec.getId(), rec.getProductId(), rec.getQuantity()))
                .limit(5)
                .collect(Collectors.toList());
        return recommendDtos;
    }

    public List<RecommendDto> addRecommend(Long productId) {
        Recommend recommend = recommendRepository.findByProductId(productId);
        if(recommend == null){
            recommendRepository.save(new Recommend(productId, 1));
        }
        return getAllRecommend();
    }
}

