package com.n11.userreviewservice.service.impl;


import com.n11.userreviewservice.client.SolrClient;
import com.n11.userreviewservice.dto.solr.RestaurantResponse;
import com.n11.userreviewservice.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final SolrClient solrClient;


    @Override
    public RestaurantResponse getWeatherData(String location) {
        return solrClient.getRecommendations(location);
    }

}
