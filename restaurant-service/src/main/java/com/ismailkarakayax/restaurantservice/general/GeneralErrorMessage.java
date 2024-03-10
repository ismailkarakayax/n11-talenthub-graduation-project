package com.ismailkarakayax.restaurantservice.general;

import lombok.Getter;

@Getter
public enum GeneralErrorMessage implements BaseErrorMessage {

    RESTAURANT_NOT_FOUND("Restaurant not found!");

    private final String message;

    GeneralErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
