package com.ismailkarakayax.restaurantservice.dto;

public record RestaurantResponse(
        String id,
        String name,
        String location,
        Double averageScore
) {
}
