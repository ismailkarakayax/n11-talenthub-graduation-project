package com.ismailkarakayax.restaurantservice.mapper;

import com.ismailkarakayax.restaurantservice.dto.RestaurantRequest;
import com.ismailkarakayax.restaurantservice.dto.RestaurantResponse;
import com.ismailkarakayax.restaurantservice.dto.UpdateRestaurantRequest;
import com.ismailkarakayax.restaurantservice.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RestaurantMapper {

    @Mapping(target = "location", expression = "java(request.latitude() + \",\" + request.longitude())")
    Restaurant convertRequestToEntity(RestaurantRequest request);
    @Mapping(target = "location", expression = "java(request.latitude() + \",\" + request.longitude())")
    Restaurant convertUpdateToEntity(@MappingTarget Restaurant restaurant, UpdateRestaurantRequest request);

    RestaurantResponse convertEntityToResponse(Restaurant restaurant);

    List<RestaurantResponse> convertEntitiesToResponse(List<Restaurant> restaurant);

}
