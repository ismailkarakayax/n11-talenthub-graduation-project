package com.n11.userreviewservice.dto.request.create;

import com.n11.userreviewservice.model.enums.Gender;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CreateUserRequest (

        @NotBlank(message = "Name cannot be blank")
        @Size(max = 100, message = "Name cannot exceed 20 characters")
        String name,

        @NotBlank(message = "Surname cannot be blank")
        @Size(max = 100, message = "Surname cannot exceed 100 characters")
        String surname,

        @NotNull(message = "Birth date cannot be null")
        @Past(message = "Birth date must be in the past")
        LocalDate birthDate,

        @Email(message = "Invalid email format")
        String email,
        @NotNull(message = "Latitude cannot be null")
        @DecimalMin(value = "-90", inclusive = true, message = "Latitude must be at least -90")
        @DecimalMax(value = "90", inclusive = true, message = "Latitude must be at most 90")
        Double latitude,

        @NotNull(message = "Longitude cannot be null")
        @DecimalMin(value = "-180", inclusive = true, message = "Longitude must be at least -180")
        @DecimalMax(value = "180", inclusive = true, message = "Longitude must be at most 180")
        Double longitude,
        Gender gender
){
}
