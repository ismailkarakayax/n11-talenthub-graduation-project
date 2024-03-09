package com.n11.userreviewservice.dto.request.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateAddressRequest(

        @NotBlank(message = "Name cannot be blank")
        @Size(max = 20, message = "Name cannot exceed 20 characters")
        String name,

        @NotBlank(message = "City cannot be blank")
        @Size(max = 40, message = "City cannot exceed 40 characters")
        String city,

        @NotNull(message = "Longitude cannot be null")
        Double longitude,

        @NotNull(message = "Latitude cannot be null")
        Double latitude
) {
}
