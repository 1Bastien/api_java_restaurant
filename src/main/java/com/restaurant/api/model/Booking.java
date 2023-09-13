package com.restaurant.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;


@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Past
    @NotNull
    private LocalDateTime date;

    @Length(min=3, max=30)
    @NotNull
    private String nameOfBooking;

    @Email
    @NotNull
    private String email;

    @Range(min=1, max=15)
    @NotNull
    private int nbGuests;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getNameOfBooking() {
		return nameOfBooking;
	}

	public void setNameOfBooking(String nameOfBooking) {
		this.nameOfBooking = nameOfBooking;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNbGuests() {
		return nbGuests;
	}

	public void setNbGuests(int nbGuests) {
		this.nbGuests = nbGuests;
	}
    
}