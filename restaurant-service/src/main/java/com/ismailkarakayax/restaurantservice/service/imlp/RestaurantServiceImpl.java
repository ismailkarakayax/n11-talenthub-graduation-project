package com.ismailkarakayax.restaurantservice.service.imlp;

import com.ismailkarakayax.restaurantservice.dto.RestaurantRequest;
import com.ismailkarakayax.restaurantservice.dto.RestaurantResponse;
import com.ismailkarakayax.restaurantservice.dto.UpdateAverageScore;
import com.ismailkarakayax.restaurantservice.dto.UpdateRestaurantRequest;
import com.ismailkarakayax.restaurantservice.exception.RestaurantNotFoundException;
import com.ismailkarakayax.restaurantservice.mapper.RestaurantMapper;
import com.ismailkarakayax.restaurantservice.model.Restaurant;
import com.ismailkarakayax.restaurantservice.repository.RestaurantRepository;
import com.ismailkarakayax.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import static com.ismailkarakayax.restaurantservice.general.GeneralErrorMessage.RESTAURANT_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Override
    public RestaurantResponse save(RestaurantRequest request) {
        Restaurant restaurant = restaurantMapper.convertRequestToEntity(request);
        restaurantRepository.save(restaurant);
        return restaurantMapper.convertEntityToResponse(restaurant);
    }

    @Override
    public List<RestaurantResponse> getAll() {
        List<Restaurant> restaurants= StreamSupport.stream(restaurantRepository.findAll().spliterator(), false)
                .toList();

        return restaurantMapper.convertEntitiesToResponse(restaurants);
    }
    @Override
    public RestaurantResponse getById(String id){
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(()-> new RestaurantNotFoundException(RESTAURANT_NOT_FOUND));

        return restaurantMapper.convertEntityToResponse(restaurant);
    }
    @Override
    public RestaurantResponse updateRestaurantById(String id, UpdateRestaurantRequest request){
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(()-> new RestaurantNotFoundException(RESTAURANT_NOT_FOUND));

        Restaurant newRestaurant = restaurantMapper.convertUpdateToEntity(restaurant,request);
        restaurantRepository.save(newRestaurant);
        return restaurantMapper.convertEntityToResponse(newRestaurant);
    }
    @Override
    public RestaurantResponse updateAverageScore(String id, UpdateAverageScore averageScore){
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(()-> new RestaurantNotFoundException(RESTAURANT_NOT_FOUND));

        restaurant.setAverageScore(averageScore.averageScore());
        restaurantRepository.save(restaurant);
        return restaurantMapper.convertEntityToResponse(restaurant);
    }
    @Override
    public void deleteById(String id){
        restaurantRepository.deleteById(id);
    }
    @Override
    public void createMockRestaurants() {
        List<Restaurant> mockRestaurants = new ArrayList<>();

        // Burada mock restoran verilerini oluşturun
        Restaurant r1 = new Restaurant(null, "Mock Restaurant 1", "41.0647143168002,28.863865490794897", 4.5);
        Restaurant r2 = new Restaurant(null, "Mock Restaurant 2", "41.06566883548766,28.875710125384835", 4.2);
        Restaurant r3 = new Restaurant(null, "Mock Restaurant 3", "41.06663951827246,28.88753330324236", 4.8);
        Restaurant r4 = new Restaurant(null, "Mock Restaurant 4", "41.06756165359459,28.899420853794673", 4.5);
        Restaurant r5 = new Restaurant(null, "Mock Restaurant 5", "41.068467598545695,28.91128694646584", 4.2);
        Restaurant r6 = new Restaurant(null, "Mock Restaurant 6", "41.06945441723467,28.92313158141844", 4.8);
        Restaurant r7 = new Restaurant(null, "Mock Restaurant 7", "41.07037651309991,28.934997673987777", 4.5);
        Restaurant r8 = new Restaurant(null, "Mock Restaurant 8", "41.071363303133836,28.946842308956448", 4.2);
        Restaurant r9 = new Restaurant(null, "Mock Restaurant 9", "41.07226919569014,28.95872985928008", 4.8);
        mockRestaurants.add(r1);
        mockRestaurants.add(r2);
        mockRestaurants.add(r3);
        mockRestaurants.add(r4);
        mockRestaurants.add(r5);
        mockRestaurants.add(r6);
        mockRestaurants.add(r7);
        mockRestaurants.add(r8);
        mockRestaurants.add(r9);

        restaurantRepository.saveAll(mockRestaurants);
    }




}
