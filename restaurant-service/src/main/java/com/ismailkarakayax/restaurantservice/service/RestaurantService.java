package com.ismailkarakayax.restaurantservice.service;

import com.ismailkarakayax.restaurantservice.dto.RestaurantRequest;
import com.ismailkarakayax.restaurantservice.dto.RestaurantResponse;

import java.util.List;


public interface RestaurantService {
    RestaurantResponse save(RestaurantRequest request);

    List<RestaurantResponse> getAll();

    void createMockRestaurants();
}
