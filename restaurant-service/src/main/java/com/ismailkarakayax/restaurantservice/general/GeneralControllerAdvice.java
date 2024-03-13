package com.ismailkarakayax.restaurantservice.general;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@ControllerAdvice
@RequiredArgsConstructor
public class  GeneralControllerAdvice extends ResponseEntityExceptionHandler {

    private final KafkaProducerService kafkaProducerService;
    private static final String KAFKA_TOPIC = "errorLog";

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

        String message = ex.getMessage();
        String description = request.getDescription(false);

        var generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        var restResponse = RestResponse.error(generalErrorMessages);

        kafkaProducerService.sendMessage(KAFKA_TOPIC,message);

        return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        // Handle validation errors during persistence time
        Map<String, String> errors = new HashMap<>();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

        for (ConstraintViolation<?> violation : violations) {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        }

        var validationErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), "Validation failed", errors.toString());
        var restResponse = RestResponse.error(validationErrorMessages);
        kafkaProducerService.sendMessage(KAFKA_TOPIC,errors.toString());


        return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<Object> handleRTExceptions(BusinessException ex, WebRequest request) {

        String message = ex.getBaseErrorMessage().getMessage();
        String description = request.getDescription(false);

        var generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        var restResponse = RestResponse.error(generalErrorMessages);
        kafkaProducerService.sendMessage(KAFKA_TOPIC,message);

        return new ResponseEntity<>(restResponse, HttpStatus.NOT_FOUND);
    }

}