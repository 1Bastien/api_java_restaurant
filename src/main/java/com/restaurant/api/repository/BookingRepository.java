package com.restaurant.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.restaurant.api.model.Booking;
import com.restaurant.api.model.Restaurant;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	@Query("SELECT b FROM Booking b WHERE b.date BETWEEN :startDate AND :endDate AND b.restaurant = :restaurant")
    List<Booking> findBookingsBetweenDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("restaurant") Restaurant restaurant);
	
	List<Booking> findByRestaurantId(Long restaurantId);
}



    

