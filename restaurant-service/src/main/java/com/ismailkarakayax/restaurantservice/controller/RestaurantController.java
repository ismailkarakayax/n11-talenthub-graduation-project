package com.ismailkarakayax.restaurantservice.controller;

import com.ismailkarakayax.restaurantservice.dto.RestaurantRequest;
import com.ismailkarakayax.restaurantservice.dto.RestaurantResponse;
import com.ismailkarakayax.restaurantservice.dto.UpdateAverageScore;
import com.ismailkarakayax.restaurantservice.dto.UpdateRestaurantRequest;
import com.ismailkarakayax.restaurantservice.general.RestResponse;
import com.ismailkarakayax.restaurantservice.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    @Operation(summary = "Create a new restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<RestResponse<RestaurantResponse>> save(@RequestBody @Valid RestaurantRequest request) {
        RestaurantResponse response = restaurantService.save(request);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all restaurants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurants found")
    })
    public ResponseEntity<RestResponse<Iterable<RestaurantResponse>>> getAll() {
        Iterable<RestaurantResponse> response = restaurantService.getAll();
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get restaurant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant found"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    public ResponseEntity<RestResponse<RestaurantResponse>> getById(@PathVariable String id) {
        RestaurantResponse response = restaurantService.getById(id);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update restaurant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<RestResponse<RestaurantResponse>> updateById(@PathVariable String id, @RequestBody @Valid UpdateRestaurantRequest request){
        RestaurantResponse response = restaurantService.updateRestaurantById(id,request);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }

    @PutMapping("/average-score/{id}")
    @Operation(summary = "Update average score of restaurant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Average score updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<RestResponse<RestaurantResponse>> updateAverageScore(@PathVariable String id, @RequestBody @Valid UpdateAverageScore request){
        RestaurantResponse response = restaurantService.updateAverageScore(id,request);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete restaurant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Restaurant deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        restaurantService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/saveMock")
    @Operation(summary = "Save mock restaurants")
    public ResponseEntity<String> saveMock() {
        restaurantService.createMockRestaurants();
        return ResponseEntity.ok("Kaydedildi");
    }
}