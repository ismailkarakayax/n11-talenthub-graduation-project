package com.ismailkarakayax.restaurantservice.controller;

import com.ismailkarakayax.restaurantservice.dto.RestaurantRequest;
import com.ismailkarakayax.restaurantservice.dto.RestaurantResponse;
import com.ismailkarakayax.restaurantservice.general.RestResponse;
import com.ismailkarakayax.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestResponse<RestaurantResponse>> save(@RequestBody RestaurantRequest request){
        RestaurantResponse response=restaurantService.save(request);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.CREATED);
    }

    @PostMapping("/saveMock")
    public String saveMock(){
        restaurantService.createMockRestaurants();
        return "Kaydedildi";
    }

    @GetMapping
    public ResponseEntity<RestResponse<Iterable<RestaurantResponse>>> getAll(){
        Iterable<RestaurantResponse> response=restaurantService.getAll();
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }

}
