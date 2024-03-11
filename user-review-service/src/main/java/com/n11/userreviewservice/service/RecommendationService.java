package com.n11.userreviewservice.service;

import com.n11.userreviewservice.dto.response.RestaurantResponse;

import java.util.List;

public interface RecommendationService {

    List<RestaurantResponse> getRecommendationByUserId(Long id);

}
