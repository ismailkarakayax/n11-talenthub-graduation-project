package com.n11.userreviewservice.dto.response;

import com.n11.userreviewservice.model.enums.Rate;

public record ReviewResponse (
        Long userId,
        Long restaurantId,
        Rate rate,
        String comment
){
}
