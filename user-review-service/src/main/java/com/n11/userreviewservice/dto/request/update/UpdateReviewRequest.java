package com.n11.userreviewservice.dto.request.update;

import com.n11.userreviewservice.model.enums.Rate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateReviewRequest (
        @NotNull(message = "Rate cannot be null")
        Rate rate,

        @Size(max = 150, message = "Comment cannot exceed 150 characters")
        String comment
) {
}
