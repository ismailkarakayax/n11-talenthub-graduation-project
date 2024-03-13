package com.n11.userreviewservice.client;

import com.n11.userreviewservice.dto.request.update.UpdateAverageScore;
import com.n11.userreviewservice.dto.response.RestaurantResponse;
import com.n11.userreviewservice.general.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "restaurant", url = "http://localhost:8082/api/v1/restaurants")
public interface RestaurantClient {

    @GetMapping("/{id}")
    ResponseEntity<RestResponse<RestaurantResponse>> getById(@PathVariable String id);

    @PutMapping("/average-score/{id}")
    ResponseEntity<RestResponse<RestaurantResponse>> updateAverageScore(@PathVariable String id, @RequestBody UpdateAverageScore request);

}
