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

import com.restaurant.api.model.Menu;
import com.restaurant.api.repository.MenuRepository;

@RestController
public class MenuController {
	@Autowired
	private MenuRepository menuRepository;
	
	//Create
	@PostMapping("/menu")
	public Menu createMenu(@RequestBody Menu menu) {
		return menuRepository.save(menu);
	}
	
	//Read Menus
	@GetMapping("/menus")
	public Iterable<Menu> getMenus(){
		return menuRepository.findAll();
	}
	
	//Read menu
	@GetMapping("/menu/{id}")
	public Menu getMenu(@PathVariable("id") long id) {
		Optional<Menu> currentMenu = menuRepository.findById(id);
		
		if(!currentMenu.isPresent()) {
			throw new IllegalArgumentException("Bad Request");
		}
		
		return currentMenu.get();
	}
	
	//Update
	@PutMapping("/menu/{id}")
	public Menu updateMenu (@PathVariable("id") long id, @RequestBody Menu menu) {
		Optional<Menu> isCurrentMenu = menuRepository.findById(id);
			
		if(!isCurrentMenu.isPresent()) {
			throw new IllegalArgumentException("Bad Request");
		}
			
		Menu currentMenu = isCurrentMenu.get();
				
		currentMenu.setName(menu.getName());
		currentMenu.setDescription(menu.getDescription());
		currentMenu.setPrice(menu.getPrice());
		currentMenu.setAvailable(menu.isAvailable());
				
		menuRepository.save(currentMenu);
			
		return currentMenu;
	}
		
	//Delete
	@DeleteMapping("/menu/{id}")
	public void deleteMenu (@PathVariable("id") long id) {
		menuRepository.deleteById(id);
	}
}
