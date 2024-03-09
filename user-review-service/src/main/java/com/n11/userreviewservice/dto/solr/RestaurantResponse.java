package com.n11.userreviewservice.dto.solr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantResponse {
    private String name;
    private String location;
    private double averageScore;
    private String id;
    @JsonProperty("_version_")
    private long version;
}
