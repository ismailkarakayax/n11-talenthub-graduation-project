package com.n11.userreviewservice.client;

import com.n11.userreviewservice.dto.solr.RestaurantResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "restaurant", url ="http://localhost:8983")
public interface SolrClient {

    @GetMapping("/solr/restaurants/select?fq=fq%3D%7B!geofilt%20pt%3D{location}%20sfield%3Dlocation%20d%3D10%7D&indent=true&q.op=OR&q=*%3A*&rows=3&sort=sum(mul(averageScore%2C14)%2Cmul(sub(10%2Cgeodist({location}%2Clocation))%2C3))%20desc")
    RestaurantResponse getRecommendations(@PathVariable String location);



}
