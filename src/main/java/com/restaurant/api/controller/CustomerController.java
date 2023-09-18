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

import com.restaurant.api.model.Customer;
import com.restaurant.api.repository.CustomerRepository;

@RestController
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	//Create
	@PostMapping("/customer")
	public Customer createCustomer(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	}
	
	//Read Customers
	@GetMapping("/customers")
	public Iterable<Customer> getCustomers(){
		return customerRepository.findAll();
	}
	
	//Read Customer
	@GetMapping("/customer/{id}")
	public Customer getCustomer(@PathVariable("id") long id) {
		Optional<Customer> currentCustomer = customerRepository.findById(id);
		
		if(!currentCustomer.isPresent()) {
			throw new IllegalArgumentException("Bad Request");
		}
		
		return currentCustomer.get();
	}
	
	//Update
	@PutMapping("/customer/{id}")
	public Customer updateCustomer (@PathVariable("id") long id, @RequestBody Customer customer) {
		Optional<Customer> isCurrentCustomer = customerRepository.findById(id);
			
		if(!isCurrentCustomer.isPresent()) {
			throw new IllegalArgumentException("Bad Request");
		}
			
		Customer currentCustomer = isCurrentCustomer.get();
				
		currentCustomer.setFirstName(customer.getFirstName());
		currentCustomer.setLastName(customer.getLastName());
		currentCustomer.setEmail(customer.getEmail());
		currentCustomer.setPassword(customer.getPassword());
		currentCustomer.setRole(customer.getRole());
		currentCustomer.setNbGuests(customer.getNbGuests());
				
		customerRepository.save(currentCustomer);
			
		return currentCustomer;
	}
		
	//Delete
	@DeleteMapping("/customer/{id}")
	public void deleteCustomer (@PathVariable("id") long id) {
		customerRepository.deleteById(id);
	}
}
