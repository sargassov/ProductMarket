package com.geekbrains.spring.web.recommend.controllers;

import com.geekbrains.spring.web.api.recommend.RecommendDto;
import com.geekbrains.spring.web.recommend.services.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recommends")
@RequiredArgsConstructor
public class RecommendController {
    private RecommendService recommendService;


    @GetMapping
    public List<RecommendDto> getAllRecommends() {
       return recommendService.getAllRecommend();
    }

    @GetMapping("/{id}")
    public void addRecommend(@PathVariable Long id) {
        recommendService.addRecommend(id);
    }

}