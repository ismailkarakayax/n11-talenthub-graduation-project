package com.ismailkarakayax.restaurantservice.exception;

import com.ismailkarakayax.restaurantservice.general.BaseErrorMessage;
import com.ismailkarakayax.restaurantservice.general.BusinessException;

public class RestaurantNotFoundException extends BusinessException {
    public RestaurantNotFoundException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
