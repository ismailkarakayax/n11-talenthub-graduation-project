package com.ismailkarakayax.restaurantservice.dto;

import javax.validation.constraints.*;

public record UpdateRestaurantRequest(
        @NotBlank(message = "Name cannot be blank")
        @Size(max = 100, message = "Name cannot exceed 100 characters")
        String name,

        @NotNull(message = "Latitude cannot be null")
        @DecimalMin(value = "-90", inclusive = true, message = "Latitude must be at least -90")
        @DecimalMax(value = "90", inclusive = true, message = "Latitude must be at most 90")
        Double latitude,

        @NotNull(message = "Longitude cannot be null")
        @DecimalMin(value = "-180", inclusive = true, message = "Longitude must be at least -180")
        @DecimalMax(value = "180", inclusive = true, message = "Longitude must be at most 180")
        Double longitude
) {
}
