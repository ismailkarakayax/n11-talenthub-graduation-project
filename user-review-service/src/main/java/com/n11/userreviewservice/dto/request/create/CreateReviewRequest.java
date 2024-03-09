package com.n11.userreviewservice.dto.request.create;

import com.n11.userreviewservice.model.enums.Rate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateReviewRequest (

        @NotNull(message = "User ID cannot be null")
        Long userId,

        @NotNull(message = "Restaurant ID cannot be null")
        Long restaurantId,

        @NotNull(message = "Rate cannot be null")
        Rate rate,

        @Size(max = 150, message = "Comment cannot exceed 150 characters")
        String comment

){
}
