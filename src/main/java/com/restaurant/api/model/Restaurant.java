package com.restaurant.api.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Restaurant{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min=3, max=30)
    @NotNull
    private String name;

    @Min(0)
    @NotNull
    private int seatingCapacity;
    
    @NotNull
    @Length(min=1, max=100)
    private String adress;
    
    private int[][] openingTime;
    
    
    public Restaurant() {
    	this.openingTime = new int[7][24];
    }
   
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public int[][] getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(int[][] openingTime) {
		for (int[] row : openingTime) {
			if (openingTime.length > 7) {
		        throw new IllegalArgumentException("Bad Request");
		    }
			 Set<Integer> uniqueValues = new HashSet<>();
            for (int value : row) {
                if (value < 0 || value > 23) {
                    throw new IllegalArgumentException("Bad Request");
                }
                if (!uniqueValues.add(value)) {
                	throw new IllegalArgumentException("Bad Request");
                }
            }
        }
		this.openingTime = openingTime;
	}
	
}
























