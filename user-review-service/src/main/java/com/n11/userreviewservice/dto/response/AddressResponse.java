package com.n11.userreviewservice.dto.response;

public record AddressResponse(
        String name,
        String city,
        Double longitude,
        Double latitude,
        Long userId
) {
}
