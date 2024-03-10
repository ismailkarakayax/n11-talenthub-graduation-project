package com.ismailkarakayax.restaurantservice.service;

import com.ismailkarakayax.restaurantservice.dto.RestaurantRequest;
import com.ismailkarakayax.restaurantservice.dto.RestaurantResponse;
import com.ismailkarakayax.restaurantservice.dto.UpdateAverageScore;
import com.ismailkarakayax.restaurantservice.dto.UpdateRestaurantRequest;

import java.util.List;


public interface RestaurantService {
    RestaurantResponse save(RestaurantRequest request);

    List<RestaurantResponse> getAll();

    RestaurantResponse getById(String id);

    RestaurantResponse updateRestaurantById(String id, UpdateRestaurantRequest request);

    RestaurantResponse updateAverageScore(String id, UpdateAverageScore averageScore);

    void deleteById(String id);

    void createMockRestaurants();
}
