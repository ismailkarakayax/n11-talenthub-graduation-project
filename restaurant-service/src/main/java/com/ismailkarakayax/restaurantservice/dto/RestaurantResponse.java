package com.ismailkarakayax.restaurantservice.dto;

public record RestaurantResponse(
        String id,
        String name,
        Double latitude,
        Double longitude,
        Double averageScore
) {
}
