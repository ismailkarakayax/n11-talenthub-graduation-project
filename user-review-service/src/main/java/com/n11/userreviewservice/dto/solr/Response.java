package com.n11.userreviewservice.dto.solr;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private int numFound;
    private int start;
    private boolean numFoundExact;
    private List<Restaurant> docs;
}
