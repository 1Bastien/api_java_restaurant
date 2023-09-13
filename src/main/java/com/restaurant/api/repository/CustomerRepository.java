package com.restaurant.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.api.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}