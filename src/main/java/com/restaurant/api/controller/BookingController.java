package com.restaurant.api.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.api.model.Booking;
import com.restaurant.api.model.Restaurant;
import com.restaurant.api.repository.BookingRepository;
import com.restaurant.api.repository.RestaurantRepository;
import com.restaurant.api.service.BookingService;
import com.restaurant.api.service.RestaurantService;

@RestController
public class BookingController {
	
	private BookingRepository bookingRepository;
	private BookingService bookingService;
	private RestaurantRepository restaurantRepository;
	private RestaurantService restaurantService;
	
	@Autowired
	public BookingController(BookingRepository bookingRepository, BookingService bookingService, RestaurantRepository restaurantRepository, RestaurantService restaurantService) {
		this.bookingRepository = bookingRepository;
		this.bookingService = bookingService;
		this.restaurantRepository = restaurantRepository;
		this.restaurantService = restaurantService;
	}
	
	
	//Create
	@PostMapping("/booking")
	public Booking createBooking(@RequestBody Booking booking) {
		
		Optional<Restaurant> isRestaurant = restaurantRepository.findById(booking.getRestaurant().getId());
		if(!isRestaurant.isPresent()) {
			throw new IllegalArgumentException("bad request");
		}
		Restaurant restaurant = isRestaurant.get();
		
		LocalDateTime dateOfBooking = booking.getDate();
		
		if (!restaurantService.isOpen(restaurant, dateOfBooking)) {
			throw new IllegalArgumentException("bad request");
		}
		
		int nbGuests = booking.getNbGuests();
		
		if (bookingService.getRemainingPlaces(restaurant.getName(), dateOfBooking.withMinute(0), dateOfBooking.withMinute(0).plusHours(1)) < nbGuests) {
			throw new IllegalArgumentException("bad request");
		}
		
		return bookingRepository.save(booking);
	}
	
	//Read Bookings
	@GetMapping("/bookings/{restaurantName}")
	public Iterable<Booking> getBookings(@PathVariable("restaurantName") String restaurantName){
		
		Optional<Restaurant> isCurrentRestaurant = restaurantRepository.findByName(restaurantName);
		if(!isCurrentRestaurant.isPresent()) {
			throw new IllegalArgumentException("Bad request");
		}
		
		Restaurant currentRestaurant = isCurrentRestaurant.get();
		
		return bookingRepository.findByRestaurantId(currentRestaurant.getId());
	}
	
	//Read booking
	@GetMapping("/booking/{id}")
	public Booking getBooking(@PathVariable("id") long id) {
		Optional<Booking> currentBooking = bookingRepository.findById(id);
		
		if(!currentBooking.isPresent()) {
			throw new IllegalArgumentException("Bad Request");
		}
		
		return currentBooking.get();
	}
	
	//Read bookings between date
	@GetMapping("/bookings/{restaurantName}/{startDate}/{endDate}")
	public int getRemainingPlaces(@PathVariable("restaurantName") String restaurantName, @PathVariable("startDate") LocalDateTime startDate, @PathVariable("endDate") LocalDateTime endDate) {
		return bookingService.getRemainingPlaces(restaurantName, startDate, endDate);
	}
	
	//Update
	@PutMapping("/booking/{id}")
	public Booking updateBooking (@PathVariable("id") long id, @RequestBody Booking booking) {
		Optional<Booking> isCurrentBooking = bookingRepository.findById(id);
			
		if(!isCurrentBooking.isPresent()) {
			throw new IllegalArgumentException("Bad Request");
		}
			
		Booking currentBooking = isCurrentBooking.get();
				
		currentBooking.setNameOfBooking(booking.getNameOfBooking());
		currentBooking.setDate(booking.getDate());
		currentBooking.setEmail(booking.getEmail());
		currentBooking.setNbGuests(booking.getNbGuests());
				
		bookingRepository.save(currentBooking);
			
		return currentBooking;
	}
		
	//Delete
	@DeleteMapping("/booking/{id}")
	public void deleteMenu (@PathVariable("id") long id) {
		bookingRepository.deleteById(id);
	}
}
