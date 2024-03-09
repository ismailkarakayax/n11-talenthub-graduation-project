package com.n11.userreviewservice.controller;


import com.n11.userreviewservice.dto.solr.RestaurantResponse;
import com.n11.userreviewservice.general.RestResponse;
import com.n11.userreviewservice.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping("/search")
    public ResponseEntity<RestResponse<SolrDocumentList>> performSolrQuery(){
        SolrDocumentList queryResponse = recommendationService.performSolrQuery();
        return new ResponseEntity<>(RestResponse.of(queryResponse), HttpStatus.OK);
    }
}
