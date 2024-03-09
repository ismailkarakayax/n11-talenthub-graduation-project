package com.ismailkarakayax.restaurantservice.dto;

public record RestaurantResponse(
        String name,
        String location,
        double averageScore
) {
}
