package com.n11.userreviewservice.service.impl;

import com.n11.userreviewservice.client.RestaurantClient;
import com.n11.userreviewservice.dto.request.create.CreateReviewRequest;
import com.n11.userreviewservice.dto.request.update.UpdateAverageScore;
import com.n11.userreviewservice.dto.request.update.UpdateReviewRequest;
import com.n11.userreviewservice.dto.response.ReviewResponse;
import com.n11.userreviewservice.mapper.ReviewMapper;
import com.n11.userreviewservice.model.Review;
import com.n11.userreviewservice.model.User;
import com.n11.userreviewservice.repository.ReviewRepository;
import com.n11.userreviewservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private UserService userService;

    @Mock
    private ReviewMapper reviewMapper;

    @Mock
    private RestaurantClient restaurantClient;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Test
    void shouldSaveReview() {
        // Arrange
        CreateReviewRequest createRequest = new CreateReviewRequest(1L, "restaurant123", 4, "Good food");
        User user=new User();
        Review createdReview = new Review(); // create a review instance as per your implementation
        ReviewResponse expectedResponse = new ReviewResponse(1L, "restaurant123", 4, "Good food");
        UpdateAverageScore averageScore=new UpdateAverageScore(4);

        when(userService.findEntityById(createRequest.userId())).thenReturn(user); // replace with your actual User instance
        when(reviewMapper.convertCreateToReview(createRequest, user)).thenReturn(createdReview);
        when(reviewRepository.save(createdReview)).thenReturn(createdReview);
        when(reviewMapper.convertToResponse(createdReview)).thenReturn(expectedResponse);

        // Act
        ReviewResponse result = reviewService.save(createRequest);

        // Assert
        assertEquals(expectedResponse, result);
        verify(reviewRepository, times(1)).save(createdReview);
        verify(reviewMapper, times(1)).convertToResponse(createdReview);

    }

    @Test
    void shouldFindReviewById() {
        // Arrange
        Long reviewId = 1L;
        Review existingReview = new Review(); // create a review instance as per your implementation
        ReviewResponse expectedResponse = new ReviewResponse(1L, "restaurant123", 4, "Good food");

        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(existingReview));
        when(reviewMapper.convertToResponse(any(Review.class))).thenReturn(expectedResponse);

        // Act
        ReviewResponse result = reviewService.findById(reviewId);

        // Assert
        assertNotNull(existingReview);
        assertEquals(expectedResponse, result);

    }

    @Test
    void shouldUpdateReview() {
        // Arrange
        Long reviewId = 1L;
        UpdateReviewRequest updateRequest = new UpdateReviewRequest(5, "Excellent service");
        Review existingReview = new Review(); // create a review instance as per your implementation
        Review updatedReview = new Review(); // create an updated review instance as per your implementation
        ReviewResponse expectedResponse = new ReviewResponse(1L, "restaurant123", 5, "Excellent service");

        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(existingReview));
        when(reviewMapper.convertUpdateToReview(existingReview, updateRequest)).thenReturn(updatedReview);
        when(reviewRepository.save(updatedReview)).thenReturn(updatedReview);
        when(reviewMapper.convertToResponse(updatedReview)).thenReturn(expectedResponse);

        // Act
        ReviewResponse result = reviewService.update(reviewId, updateRequest);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponse, result);
        verify(reviewRepository, times(1)).save(updatedReview);
        verify(reviewMapper, times(1)).convertToResponse(updatedReview);
    }

    @Test
    void shouldDeleteReviewById() {
        // Arrange
        Long reviewId = 1L;
        Review existingReview = new Review(); // create a review instance as per your implementation
        double averageScore = 0;

        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(existingReview));

        // Act
        reviewService.deleteById(reviewId);

        // Assert
        verify(reviewRepository, times(1)).deleteById(reviewId);

    }

    @Test
    void shouldUpdateAverageScore_whenReviewsExist() {
        // Arrange
        String restaurantId = "restaurant123";
        int existingReviewCount = 3;
        double existingScore = 12.0;

        when(reviewRepository.countByRestaurantId(restaurantId)).thenReturn(existingReviewCount);
        when(reviewRepository.findAllRateByRestaurantId(restaurantId)).thenReturn(existingScore);

        // Act
        reviewService.updateAverageScore(restaurantId);

        // Assert
        verify(restaurantClient, times(1)).updateAverageScore(eq(restaurantId), any(UpdateAverageScore.class));
        verify(reviewRepository, times(1)).findAllRateByRestaurantId(restaurantId);
    }

    @Test
    void shouldSetZeroScore_whenNoReviewsExist() {
        // Arrange
        String restaurantId = "restaurant123";
        int existingReviewCount = 0;

        when(reviewRepository.countByRestaurantId(restaurantId)).thenReturn(existingReviewCount);

        // Act
        reviewService.updateAverageScore(restaurantId);

        // Assert
        verify(restaurantClient, times(1)).updateAverageScore(eq(restaurantId), any(UpdateAverageScore.class));
        verify(reviewRepository, never()).findAllRateByRestaurantId(restaurantId);
    }

}