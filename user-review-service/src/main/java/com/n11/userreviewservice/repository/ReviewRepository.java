package com.n11.userreviewservice.repository;

import com.n11.userreviewservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByRestaurantId(String id);

    @Query("SELECT AVG(r.score) FROM Review r WHERE r.restaurantId = :restaurantId")
    Double findAverageRateByRestaurantId(@Param("restaurantId") String restaurantId);

}
