package com.geekbrains.spring.web.recommend.repositories;

import com.geekbrains.spring.web.recommend.entites.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecommendRepository extends JpaRepository<Recommend, Long> {
    @Query("select r from Recommend r where r.productId = ?1")
    Recommend findByProductId(Long productId);
}
