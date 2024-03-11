package com.n11.userreviewservice.dto.response;


public record RestaurantResponse (
        String id,
        String name,
        String location,
        Double averageScore
){
}
