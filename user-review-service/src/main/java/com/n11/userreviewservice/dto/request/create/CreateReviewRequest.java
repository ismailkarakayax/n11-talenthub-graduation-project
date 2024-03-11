package com.n11.userreviewservice.dto.request.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateReviewRequest (

        @NotNull(message = "User ID cannot be null")
        Long userId,

        @NotNull(message = "Restaurant ID cannot be null")
        String restaurantId,

        @NotNull(message = "Score cannot be null")
        int score,

        @Size(max = 150, message = "Comment cannot exceed 150 characters")
        String comment

){
}
