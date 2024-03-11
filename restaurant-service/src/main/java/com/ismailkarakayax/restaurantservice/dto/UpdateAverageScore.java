package com.ismailkarakayax.restaurantservice.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.PositiveOrZero;

public record UpdateAverageScore(
        @PositiveOrZero(message = "Average score must be a non-negative value")
        @DecimalMin(value = "0", inclusive = true, message = "Average score must be at least 0")
        @DecimalMax(value = "5", inclusive = true, message = "Average score must be at most 5")
        double averageScore
) {
}
