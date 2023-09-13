package com.restaurant.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.api.model.Menu;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long> {

}