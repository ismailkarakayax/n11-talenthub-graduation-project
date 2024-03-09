package com.n11.userreviewservice.service;

import com.n11.userreviewservice.dto.solr.RestaurantResponse;

public interface RecommendationService {

    RestaurantResponse getWeatherData(String location);
}
