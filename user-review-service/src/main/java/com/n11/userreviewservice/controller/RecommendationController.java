package com.n11.userreviewservice.controller;


import com.n11.userreviewservice.dto.response.RestaurantResponse;
import com.n11.userreviewservice.general.RestResponse;
import com.n11.userreviewservice.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<List<RestaurantResponse>>> getRecommendationsByUserID(@PathVariable Long id) {
        List<RestaurantResponse> queryResponse = recommendationService.getRecommendationByUserId(id);
        return new ResponseEntity<>(RestResponse.of(queryResponse), HttpStatus.OK);
    }
}
