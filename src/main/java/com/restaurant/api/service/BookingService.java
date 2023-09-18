package com.restaurant.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.api.model.Booking;
import com.restaurant.api.model.Restaurant;
import com.restaurant.api.repository.BookingRepository;
import com.restaurant.api.repository.RestaurantRepository;

@Service
public class BookingService {
	
	private BookingRepository bookingRepository;
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	public BookingService(BookingRepository bookingRepository, RestaurantRepository restaurantRepository) {
		this.bookingRepository = bookingRepository;
		this.restaurantRepository = restaurantRepository;
	}
	
	public int getRemainingPlaces(String restaurantName, LocalDateTime startDate, LocalDateTime endDate) {
		
		
		Optional<Restaurant> isCurrentRestaurant = restaurantRepository.findByName(restaurantName);
		if(!isCurrentRestaurant.isPresent()) {
			throw new IllegalArgumentException("bad request");
		}
		
		Restaurant currentRestaurant = isCurrentRestaurant.get();
		
		List<Booking> bookings = bookingRepository.findBookingsBetweenDate(startDate, endDate, currentRestaurant);
		int totalGuestsBook = bookings.stream().mapToInt(Booking::getNbGuests).sum();
		
		int seatingCapacity = currentRestaurant.getSeatingCapacity();
		
		System.out.println(totalGuestsBook);
		System.out.println(seatingCapacity);
		
		return seatingCapacity - totalGuestsBook;
	}
}