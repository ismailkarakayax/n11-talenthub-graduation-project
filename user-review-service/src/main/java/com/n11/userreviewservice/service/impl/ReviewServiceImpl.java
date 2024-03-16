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
import org.springframework.transaction.annotation.Transactional;

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
    public List<ReviewResponse> findAll() {
        List<Review> reviews = reviewRepository.findAll();

        return reviewMapper.convertToResponseList(reviews);
    }

    @Override
    public List<ReviewResponse> findAllByRestaurantId(String id) {
        List<Review> reviews = reviewRepository.findAllByRestaurantId(id);
        return reviewMapper.convertToResponseList(reviews);
    }

    @Override
    @Transactional
    public ReviewResponse save(CreateReviewRequest request) {
        User entity = userService.findEntityById(request.userId());
        Review review = reviewMapper.convertCreateToReview(request, entity);
        reviewRepository.save(review);
        updateAverageScore(review.getRestaurantId());
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
        reviewRepository.save(review);
        updateAverageScore(review.getRestaurantId());
        return reviewMapper.convertToResponse(review);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(REVIEW_NOT_FOUND));
        reviewRepository.deleteById(id);
        updateAverageScore(review.getRestaurantId());
    }

    public void updateAverageScore(String restaurantId) {
        int restaurantReviewCount = reviewRepository.countByRestaurantId(restaurantId);

        if (restaurantReviewCount > 0) {
            Double allScore = reviewRepository.findAllRateByRestaurantId(restaurantId);
            UpdateAverageScore updateAverageScore = new UpdateAverageScore(allScore / restaurantReviewCount);
            restaurantClient.updateAverageScore(restaurantId, updateAverageScore);
        } else {
            UpdateAverageScore updateAverageScore = new UpdateAverageScore(0);
            restaurantClient.updateAverageScore(restaurantId, updateAverageScore);
        }
    }
}
