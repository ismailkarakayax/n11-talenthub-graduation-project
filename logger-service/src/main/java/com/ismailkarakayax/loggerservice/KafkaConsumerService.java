package com.ismailkarakayax.loggerservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final ErrorLogRepository errorLogRepository;

    @KafkaListener(topics = "userReviewErrorLog", groupId = "log-consumer-group")
    public void consumesUserReviewService(String message){

        log.info("consume start!");

        ErrorLog errorLog = new ErrorLog();
        errorLog.setDate(LocalDateTime.now());
        errorLog.setMessage(message);
        errorLog.setDescription("User Review Service Error Logs");

        errorLogRepository.save(errorLog);

        log.info("consume finished!");
    }

    @KafkaListener(topics = "restaurantErrorLog", groupId = "log-consumer-group")
    public void consumesRestaurant(String message){

        log.info("consume start!");

        ErrorLog errorLog = new ErrorLog();
        errorLog.setDate(LocalDateTime.now());
        errorLog.setMessage(message);
        errorLog.setDescription("Restaurant Service Error Logs");

        errorLogRepository.save(errorLog);

        log.info("consume finished!");
    }

}
