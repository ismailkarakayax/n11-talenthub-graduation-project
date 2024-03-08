package com.n11.userreviewservice.exception;

import com.n11.userreviewservice.general.BaseErrorMessage;
import com.n11.userreviewservice.general.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AddressNotFoundException extends BusinessException {

    public AddressNotFoundException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
