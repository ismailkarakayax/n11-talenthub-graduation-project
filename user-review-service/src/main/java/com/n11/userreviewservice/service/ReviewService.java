package com.n11.userreviewservice.service;

import com.n11.userreviewservice.dto.request.create.CreateReviewRequest;
import com.n11.userreviewservice.dto.request.update.UpdateReviewRequest;
import com.n11.userreviewservice.dto.response.ReviewResponse;

import java.util.List;


public interface ReviewService {
    List<ReviewResponse> findAll();
    List<ReviewResponse> findAllByRestaurantId(String id);
    ReviewResponse save(CreateReviewRequest request);

    ReviewResponse findById(Long id);

    ReviewResponse update(Long id, UpdateReviewRequest request);

    void deleteById(Long id);



}
