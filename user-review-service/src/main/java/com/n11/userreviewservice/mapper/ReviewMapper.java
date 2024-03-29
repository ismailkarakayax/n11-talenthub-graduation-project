package com.n11.userreviewservice.mapper;

import com.n11.userreviewservice.dto.request.create.CreateReviewRequest;
import com.n11.userreviewservice.dto.request.update.UpdateReviewRequest;
import com.n11.userreviewservice.dto.response.ReviewResponse;
import com.n11.userreviewservice.model.Review;
import com.n11.userreviewservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "user", source = "user")
    @Mapping(target = "id", ignore = true)
    Review convertCreateToReview(CreateReviewRequest request, User user);

    Review convertUpdateToReview(@MappingTarget Review oldReview, UpdateReviewRequest request);

    @Mapping(target = "userId",source = "review.user.id")
    @Mapping(target= "userFullName", expression = "java(review.getUser().getName()+\" \"+review.getUser().getSurname())")
    ReviewResponse convertToResponse(Review review);

    List<ReviewResponse> convertToResponseList(List<Review> reviews);
}
