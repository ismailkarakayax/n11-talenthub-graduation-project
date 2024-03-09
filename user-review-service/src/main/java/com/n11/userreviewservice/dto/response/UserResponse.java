package com.n11.userreviewservice.dto.response;

import com.n11.userreviewservice.model.enums.Gender;
import com.n11.userreviewservice.model.enums.Status;

import java.time.LocalDate;

public record UserResponse(
        String name,
        String surname,
        LocalDate birthDate,
        String email,
        Gender gender,
        Status status


) {
}
