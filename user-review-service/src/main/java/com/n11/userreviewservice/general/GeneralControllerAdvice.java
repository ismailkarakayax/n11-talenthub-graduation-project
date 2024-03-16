package com.n11.userreviewservice.general;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class  GeneralControllerAdvice  {

    private final KafkaProducerService kafkaProducerService;
    private static final String KAFKA_TOPIC = "userReviewErrorLog";

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

        String message = ex.getMessage();
        String description = request.getDescription(false);

        var generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        var restResponse = RestResponse.error(generalErrorMessages);

        kafkaProducerService.sendMessage(KAFKA_TOPIC,message);

        return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> errors = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            String errorMessage = fieldError.getDefaultMessage();
            errors.add(errorMessage);
        }

        var errorMessages = new GeneralErrorMessages(LocalDateTime.now(), "Validation failed", errors.toString());
        var restResponse = RestResponse.error(errorMessages);
        kafkaProducerService.sendMessage(KAFKA_TOPIC, errors.toString());

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