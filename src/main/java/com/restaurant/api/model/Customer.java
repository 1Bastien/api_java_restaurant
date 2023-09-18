package com.restaurant.api.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min=3, max=30)
    @NotNull
    private String firstName;

    @Length(min=3, max=30)
    @NotNull
    private String lastName;

    @Email
    @NotNull
    @Column(unique = true)
    private String email;

    @Length(min=6, max=100)
    @NotNull
    private String password;
    
    @NotNull
    private String role;
    
    @Range(min=1, max=15)
    @NotNull
    private int nbGuests;
    
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getNbGuests() {
		return nbGuests;
	}

	public void setNbGuests(int nbGuests) {
		this.nbGuests = nbGuests;
	}

}