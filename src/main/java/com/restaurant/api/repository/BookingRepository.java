package com.restaurant.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.api.model.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {

}