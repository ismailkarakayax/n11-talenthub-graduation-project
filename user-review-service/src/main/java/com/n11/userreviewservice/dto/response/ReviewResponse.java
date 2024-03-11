package com.n11.userreviewservice.dto.response;

public record ReviewResponse (
        Long userId,
        String restaurantId,
        int score,
        String comment
){
}
