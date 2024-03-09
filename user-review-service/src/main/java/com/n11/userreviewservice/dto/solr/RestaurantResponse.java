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

    @JsonProperty("responseHeader")
    private ResponseHeader responseHeader;
    private Response response;
}
