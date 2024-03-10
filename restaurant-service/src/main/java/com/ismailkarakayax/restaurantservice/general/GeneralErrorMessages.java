package com.ismailkarakayax.restaurantservice.general;

import java.time.LocalDateTime;

public record GeneralErrorMessages(LocalDateTime date, String message, String description) {

}