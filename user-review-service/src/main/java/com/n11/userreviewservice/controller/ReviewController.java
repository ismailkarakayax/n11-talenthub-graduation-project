package com.n11.userreviewservice.controller;

import com.n11.userreviewservice.dto.request.create.CreateReviewRequest;
import com.n11.userreviewservice.dto.request.update.UpdateReviewRequest;

import com.n11.userreviewservice.dto.response.ReviewResponse;

import com.n11.userreviewservice.general.RestResponse;
import com.n11.userreviewservice.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<RestResponse<ReviewResponse>> save(@RequestBody @Valid CreateReviewRequest request) {
        ReviewResponse response = reviewService.save(request);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<ReviewResponse>> findById(@PathVariable Long id) {
        ReviewResponse response = reviewService.findById(id);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RestResponse<ReviewResponse>> updateById(@PathVariable Long id, @RequestBody @Valid UpdateReviewRequest request) {
        ReviewResponse response = reviewService.update(id, request);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        reviewService.deleteById(id);
    }
}
