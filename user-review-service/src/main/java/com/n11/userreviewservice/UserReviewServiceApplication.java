package com.n11.userreviewservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserReviewServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserReviewServiceApplication.class, args);
	}

}
