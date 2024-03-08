package com.n11.userreviewservice.general;

import lombok.Getter;

@Getter
public enum GeneralErrorMessage implements BaseErrorMessage {

    USER_NOT_FOUND("User not found!"),
    ADDRESS_NOT_FOUND("Address not found!"),
    REVIEW_NOT_FOUND("Review not found!");

    private final String message;

    GeneralErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
