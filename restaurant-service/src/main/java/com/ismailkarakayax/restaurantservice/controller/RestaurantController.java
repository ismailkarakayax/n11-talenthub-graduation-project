package com.ismailkarakayax.restaurantservice.controller;

import com.ismailkarakayax.restaurantservice.dto.RestaurantRequest;
import com.ismailkarakayax.restaurantservice.dto.RestaurantResponse;
import com.ismailkarakayax.restaurantservice.dto.UpdateAverageScore;
import com.ismailkarakayax.restaurantservice.dto.UpdateRestaurantRequest;
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
    public ResponseEntity<RestResponse<RestaurantResponse>> save(@RequestBody RestaurantRequest request) {
        RestaurantResponse response = restaurantService.save(request);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<RestResponse<Iterable<RestaurantResponse>>> getAll() {
        Iterable<RestaurantResponse> response = restaurantService.getAll();
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<RestaurantResponse>> getById(@PathVariable String id) {
        RestaurantResponse response = restaurantService.getById(id);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<RestaurantResponse>> updateById(@PathVariable String id, @RequestBody UpdateRestaurantRequest request){
        RestaurantResponse response = restaurantService.updateRestaurantById(id,request);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RestResponse<RestaurantResponse>> updateAverageScore(@PathVariable String id, @RequestBody UpdateAverageScore request){
        RestaurantResponse response = restaurantService.updateAverageScore(id,request);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id){
        restaurantService.deleteById(id);
    }


    @PostMapping("/saveMock")
    public String saveMock() {
        restaurantService.createMockRestaurants();
        return "Kaydedildi";
    }

}
