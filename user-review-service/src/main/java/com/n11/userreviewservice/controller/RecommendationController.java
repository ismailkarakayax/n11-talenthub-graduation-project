package com.n11.userreviewservice.controller;


import com.n11.userreviewservice.dto.solr.RestaurantResponse;
import com.n11.userreviewservice.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping()
    public RestaurantResponse getWeatherData(@RequestParam String location) {
        return recommendationService.getWeatherData(location);
    }
}
