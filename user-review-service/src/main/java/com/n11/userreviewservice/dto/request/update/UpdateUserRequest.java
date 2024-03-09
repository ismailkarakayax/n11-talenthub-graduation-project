package com.n11.userreviewservice.dto.request.update;

import com.n11.userreviewservice.model.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UpdateUserRequest (
        @NotBlank(message = "Name cannot be blank")
        @Size(max = 20, message = "Name cannot exceed 20 characters")
        String name,

        @NotBlank(message = "Surname cannot be blank")
        @Size(max = 100, message = "Surname cannot exceed 100 characters")
        String surname,

        @NotNull(message = "Birth date cannot be null")
        LocalDate birthDate,

        @Email(message = "Invalid email format")
        String email,

        Gender gender
){
}
