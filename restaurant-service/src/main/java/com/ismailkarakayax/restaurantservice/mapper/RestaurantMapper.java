package com.ismailkarakayax.restaurantservice.mapper;

import com.ismailkarakayax.restaurantservice.dto.RestaurantRequest;
import com.ismailkarakayax.restaurantservice.dto.RestaurantResponse;
import com.ismailkarakayax.restaurantservice.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RestaurantMapper {

    Restaurant convertRequestToEntity(RestaurantRequest request);
    RestaurantResponse convertEntityToResponse(Restaurant restaurant);

    List<RestaurantResponse> convertEntitiesToResponse(List<Restaurant> restaurant);

}
