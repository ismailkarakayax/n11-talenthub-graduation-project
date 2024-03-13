package com.n11.userreviewservice.repository;

import com.n11.userreviewservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Query("SELECT SUM(r.score) FROM Review r WHERE r.restaurantId = :restaurantId")
    Double findAllRateByRestaurantId(@Param("restaurantId") String restaurantId);

    int countByRestaurantId(String s);
}
