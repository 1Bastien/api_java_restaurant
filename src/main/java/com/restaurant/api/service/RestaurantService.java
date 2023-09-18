package com.restaurant.api.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.restaurant.api.model.Restaurant;

@Service
public class RestaurantService {
	
	public boolean isOpen(Restaurant restaurant, LocalDateTime dateOfBooking) {
		int dayOfWeek = dateOfBooking.getDayOfWeek().getValue() - 1;
		int hourOfBooking = dateOfBooking.getHour();
		int[][] openingTime = restaurant.getOpeningTime();
		
		boolean isOpen = false;
		
		for (int hour : openingTime[dayOfWeek]) {
			if (hour == hourOfBooking) {
				isOpen = true;
				break;
		    }
		}		
				
		return isOpen;
	}
}
