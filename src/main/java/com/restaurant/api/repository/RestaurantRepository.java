package com.restaurant.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.api.model.Restaurant;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

}