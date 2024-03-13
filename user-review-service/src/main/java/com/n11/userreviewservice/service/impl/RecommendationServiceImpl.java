package com.n11.userreviewservice.service.impl;

import com.n11.userreviewservice.client.SolrJClient;
import com.n11.userreviewservice.dto.response.RestaurantResponse;
import com.n11.userreviewservice.dto.response.UserResponse;
import com.n11.userreviewservice.service.RecommendationService;
import com.n11.userreviewservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final UserService userService;
    private final SolrJClient solrJClient;

    @Override
    public List<RestaurantResponse> getRecommendationByUserId(Long id){
        UserResponse response = userService.findById(id);

        return solrJClient.createSolrQuery(response.latitude(),response.longitude());
    }


}
