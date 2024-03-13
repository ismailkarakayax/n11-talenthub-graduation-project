package com.ismailkarakayax.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteLocatorConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                // User Service routes
                .route("user-service-find-by-id", r -> r.path("/api/v1/users/{id}")
                        .and().method("GET")
                        .uri("http://localhost:8081"))
                .route("user-service-save", r -> r.path("/api/v1/users")
                        .and().method("POST")
                        .uri("http://localhost:8081"))
                .route("user-service-update", r -> r.path("/api/v1/users/{id}")
                        .and().method("PATCH")
                        .uri("http://localhost:8081"))
                .route("user-service-deactivate", r -> r.path("/api/v1/users/activate/{id}")
                        .and().method("PATCH")
                        .uri("http://localhost:8081"))
                .route("user-service-delete", r -> r.path("/api/v1/users/{id}")
                        .and().method("DELETE")
                        .uri("http://localhost:8081"))

                .route("review-service-save", r -> r.path("/api/v1/reviews")
                        .and().method("POST")
                        .uri("http://localhost:8081"))
                .route("review-service-find-by-id", r -> r.path("/api/v1/reviews/{id}")
                        .and().method("GET")
                        .uri("http://localhost:8081"))
                .route("review-service-update", r -> r.path("/api/v1/reviews/{id}")
                        .and().method("PATCH")
                        .uri("http://localhost:8081"))
                .route("review-service-delete", r -> r.path("/api/v1/reviews/{id}")
                        .and().method("DELETE")
                        .uri("http://localhost:8081"))

                .route("recommendation-service-findAll",r->r.path("/api/v1/recommendations/{id}")
                        .and().method("GET")
                        .uri("http://localhost:8081"))

                // Restaurant Service routes
                .route("restaurant-service-save", r -> r.path("/api/v1/restaurants")
                        .and().method("POST")
                        .uri("http://localhost:8082"))
                .route("restaurant-service-get-all", r -> r.path("/api/v1/restaurants")
                        .and().method("GET")
                        .uri("http://localhost:8082"))
                .route("restaurant-service-get-by-id", r -> r.path("/api/v1/restaurants/{id}")
                        .and().method("GET")
                        .uri("http://localhost:8082"))
                .route("restaurant-service-update", r -> r.path("/api/v1/restaurants/{id}")
                        .and().method("PUT")
                        .uri("http://localhost:8082"))
                .route("restaurant-service-update-average-score", r -> r.path("/api/v1/restaurants/average-score/{id}")
                        .and().method("PUT")
                        .uri("http://localhost:8082"))
                .route("restaurant-service-delete", r -> r.path("/api/v1/restaurants/{id}")
                        .and().method("DELETE")
                        .uri("http://localhost:8082"))
                .build();
    }

}
