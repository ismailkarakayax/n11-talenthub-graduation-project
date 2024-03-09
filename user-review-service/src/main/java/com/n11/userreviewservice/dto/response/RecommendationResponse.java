package com.n11.userreviewservice.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecommendationResponse {
        private String id;
        private String name;
        private String location;
        private Double averageScore;
        private Double distance;
        private Double sortScore;
}
