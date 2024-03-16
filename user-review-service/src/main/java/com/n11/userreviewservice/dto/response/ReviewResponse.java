package com.n11.userreviewservice.dto.response;

public record ReviewResponse (
        Long id,
        Long userId,
        String userFullName,
        String restaurantId,
        int score,
        String comment
){
}
