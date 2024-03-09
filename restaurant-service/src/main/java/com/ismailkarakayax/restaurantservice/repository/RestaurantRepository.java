package com.ismailkarakayax.restaurantservice.repository;

import com.ismailkarakayax.restaurantservice.model.Restaurant;
import org.springframework.data.solr.repository.SolrCrudRepository;


public interface RestaurantRepository extends SolrCrudRepository<Restaurant,String> {


}
