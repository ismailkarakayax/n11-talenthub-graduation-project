package com.n11.userreviewservice.dto.request.update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateReviewRequest (
        @NotNull(message = "Score cannot be null")
        int score,

        @Size(max = 150, message = "Comment cannot exceed 150 characters")
        String comment
) {
}
