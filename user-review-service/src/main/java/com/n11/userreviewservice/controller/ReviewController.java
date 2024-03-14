package com.n11.userreviewservice.controller;

import com.n11.userreviewservice.dto.request.create.CreateReviewRequest;
import com.n11.userreviewservice.dto.request.update.UpdateReviewRequest;

import com.n11.userreviewservice.dto.response.ReviewResponse;

import com.n11.userreviewservice.general.RestResponse;
import com.n11.userreviewservice.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    @Operation(summary = "Get all reviews")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reviews found")
    })
    @GetMapping
    public ResponseEntity<RestResponse<List<ReviewResponse>>> findAll() {
        List<ReviewResponse> response = reviewService.findAll();
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }
    @Operation(summary = "Get reviews by restaurant ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reviews found"),
            @ApiResponse(responseCode = "404", description = "No reviews found for the given restaurant ID")
    })
    @GetMapping("/restaurantId/{id}")
    public ResponseEntity<RestResponse<List<ReviewResponse>>> findAllByRestaurantId(@PathVariable String id) {
        List<ReviewResponse> response = reviewService.findAllByRestaurantId(id);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }
    @Operation(summary = "Create a new review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Review created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Request body to create a new review",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CreateReviewRequest.class),
                    examples = {
                            @ExampleObject(
                                    name = "new Review",
                                    summary = "CreateReviewRequest",
                                    description = "Complete request with all available " +
                                            "fields for Review",
                                    value = """
                                            {
                                            "userId": "1",
                                            "restaurantId": "ef4d11d5-e022-487c-88c0-e561b5c763ff",
                                            "score": 5,
                                            "comment": "Güzel"
                                            }"""
                            )
                    }
            )
    )
    @PostMapping
    public ResponseEntity<RestResponse<ReviewResponse>> save(@RequestBody @Valid CreateReviewRequest request) {
        ReviewResponse response = reviewService.save(request);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.CREATED);
    }
    @Operation(summary = "Get review by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review found"),
            @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<ReviewResponse>> findById(@PathVariable Long id) {
        ReviewResponse response = reviewService.findById(id);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }
    @Operation(summary = "Update review by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Request body to update a new review",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UpdateReviewRequest.class),
                    examples = {
                            @ExampleObject(
                                    name = "update Review",
                                    summary = "UpdateReviewRequest",
                                    description = "Complete request with all available " +
                                            "fields for update Review",
                                    value = """
                                            {
                                            "score": 5,
                                            "comment": "Güzel"
                                             }"""
                            )
                    }
            )
    )
    @PatchMapping("/{id}")
    public ResponseEntity<RestResponse<ReviewResponse>> updateById(@PathVariable Long id, @RequestBody @Valid UpdateReviewRequest request) {
        ReviewResponse response = reviewService.update(id, request);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }
    @Operation(summary = "Delete review by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Review deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        reviewService.deleteById(id);
    }
}
