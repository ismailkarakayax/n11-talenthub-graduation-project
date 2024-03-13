package com.n11.userreviewservice.service.impl;

import com.n11.userreviewservice.client.SolrJClient;
import com.n11.userreviewservice.dto.response.RestaurantResponse;
import com.n11.userreviewservice.dto.response.UserResponse;
import com.n11.userreviewservice.model.enums.Gender;
import com.n11.userreviewservice.model.enums.Status;
import com.n11.userreviewservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecommendationServiceImplTest {

    @Mock
    private UserService userService;

    @Mock
    private SolrJClient solrJClient;

    @InjectMocks
    private RecommendationServiceImpl recommendationService;



    @Test
    void getRecommendationByUserId_shouldReturnRecommendations_whenUserExists() {
        // Arrange
        Long userId = 1L;
        UserResponse userResponse = new UserResponse("John", "Doe", LocalDate.now(), "john.doe@example.com", 40.0, -75.0, Gender.MALE, Status.ACTIVE);
        List<RestaurantResponse> mockRestaurantResponses = createMockRestaurantResponses();

        when(userService.findById(userId)).thenReturn(userResponse);
        when(solrJClient.createSolrQuery(userResponse.latitude(), userResponse.longitude())).thenReturn(mockRestaurantResponses);

        // Act
        List<RestaurantResponse> result = recommendationService.getRecommendationByUserId(userId);

        // Assert
        assertEquals(mockRestaurantResponses, result);
        verify(userService, times(1)).findById(userId);
        verify(solrJClient, times(1)).createSolrQuery(userResponse.latitude(), userResponse.longitude());
        verifyNoMoreInteractions(userService, solrJClient);
    }

    private List<RestaurantResponse> createMockRestaurantResponses() {
        // Create and return a list of mock RestaurantResponse objects
        // You can customize this method based on your test requirements
        return List.of(
                new RestaurantResponse("1", "Restaurant 1", "Location 1", 4.2),
                new RestaurantResponse("2", "Restaurant 2", "Location 2", 4.5),
                new RestaurantResponse("3", "Restaurant 3", "Location 3", 4.0)
        );
    }
}