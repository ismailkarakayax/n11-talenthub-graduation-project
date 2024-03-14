package com.n11.userreviewservice.dto.response;

import com.n11.userreviewservice.model.enums.Gender;
import com.n11.userreviewservice.model.enums.Status;

import java.time.LocalDate;

public record UserResponse(
        Long id,
        String name,
        String surname,
        LocalDate birthDate,
        String email,
        Double latitude,
        Double longitude,
        Gender gender,
        Status status


) {
}
