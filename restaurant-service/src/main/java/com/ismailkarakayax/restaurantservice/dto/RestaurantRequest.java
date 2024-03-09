package com.ismailkarakayax.restaurantservice.dto;

public record RestaurantRequest(
        String name,
        String location,
        double averageScore
) {
}
