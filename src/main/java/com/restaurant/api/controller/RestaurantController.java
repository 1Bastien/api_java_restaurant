package com.restaurant.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.api.model.Restaurant;
import com.restaurant.api.repository.RestaurantRepository;

@RestController
public class RestaurantController {

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	//Create
	@PostMapping("/restaurant")
	public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
		restaurantRepository.save(restaurant);
		return restaurant;
	}
	
	//Read Restaurants
	@GetMapping("/restaurants")
	public Iterable<Restaurant> getRestaurants() {
		return restaurantRepository.findAll();
	}
	
	//Read Restaurant
	@GetMapping("/restaurant/{id}")
	public Restaurant getRestaurant(@PathVariable("id") long id) {
		Optional<Restaurant> currentRestaurant = restaurantRepository.findById(id);
		
		if(!currentRestaurant.isPresent()) {
			throw new IllegalArgumentException("Bad Request");
		}
		
		return currentRestaurant.get();
	}
	
	//Update
	@PutMapping("restaurant/{id}")
	public Restaurant updateRestaurant (@PathVariable("id") long id, @RequestBody Restaurant restaurant) {
		Optional<Restaurant> isCurrentRestaurant = restaurantRepository.findById(id);
		
		if(!isCurrentRestaurant.isPresent()) {
			throw new IllegalArgumentException("Bad Request");
		}
		
		Restaurant currentRestaurant = isCurrentRestaurant.get();
			
		currentRestaurant.setName(restaurant.getName());
		currentRestaurant.setSeatingCapacity(restaurant.getSeatingCapacity());
		currentRestaurant.setAdress(restaurant.getAdress());
		currentRestaurant.setOpeningTime(restaurant.getOpeningTime());
			
		restaurantRepository.save(currentRestaurant);
		
		return currentRestaurant;
	}
	
	//Delete
	@DeleteMapping("/restaurant/{id}")
	public void deleteRestaurant (@PathVariable("id") long id) {
		restaurantRepository.deleteById(id);
	}
}
