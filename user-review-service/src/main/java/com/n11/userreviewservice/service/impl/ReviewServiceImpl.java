package com.n11.userreviewservice.service.impl;

import com.n11.userreviewservice.client.RestaurantClient;
import com.n11.userreviewservice.dto.request.create.CreateReviewRequest;
import com.n11.userreviewservice.dto.request.update.UpdateAverageScore;
import com.n11.userreviewservice.dto.request.update.UpdateReviewRequest;
import com.n11.userreviewservice.dto.response.ReviewResponse;
import com.n11.userreviewservice.exception.ReviewNotFoundException;
import com.n11.userreviewservice.mapper.ReviewMapper;
import com.n11.userreviewservice.model.Review;
import com.n11.userreviewservice.model.User;
import com.n11.userreviewservice.repository.ReviewRepository;
import com.n11.userreviewservice.service.ReviewService;
import com.n11.userreviewservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.n11.userreviewservice.general.GeneralErrorMessage.REVIEW_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final ReviewMapper reviewMapper;
    private final RestaurantClient restaurantClient;

    @Override
    public ReviewResponse save(CreateReviewRequest request) {
        User entity = userService.findEntityById(request.userId());
        Review review = reviewMapper.convertCreateToReview(request, entity);
        List<Review> restaurantReviews = reviewRepository.findByRestaurantId(request.restaurantId());


        if(!restaurantReviews.isEmpty()){
            Double averageScore = reviewRepository.findAverageRateByRestaurantId(request.restaurantId());
            UpdateAverageScore updateAverageScore=new UpdateAverageScore(averageScore+request.score());
            restaurantClient.updateAverageScore(request.restaurantId(), updateAverageScore);
        }else{
            UpdateAverageScore updateAverageScore=new UpdateAverageScore(request.score());
            restaurantClient.updateAverageScore(request.restaurantId(),updateAverageScore);
        }


        reviewRepository.save(review);
        return reviewMapper.convertToResponse(review);
    }

    @Override
    public ReviewResponse findById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(REVIEW_NOT_FOUND));

        return reviewMapper.convertToResponse(review);
    }

    @Override
    public ReviewResponse update(Long id, UpdateReviewRequest request) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(REVIEW_NOT_FOUND));
        review = reviewMapper.convertUpdateToReview(review, request);
        return reviewMapper.convertToResponse(review);
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }
}
