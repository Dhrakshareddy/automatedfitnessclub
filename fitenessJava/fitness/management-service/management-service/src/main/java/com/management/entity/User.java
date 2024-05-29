package com.management.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "user_profile")
public class User implements Serializable {

	private static final Long serialVersionUID = -1516965327693370237L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "user_name", unique = true)
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "contact_number", unique = true)
	private Long contactNumber;

	@Column(name = "fitnessActivity")
	private String fitnessActivity;
	
	@Column(name = "role")
	private String role;

	@Column(name = "trainer_code")
	private String trainerCode;

	@Column(name = "doctor_code")
	private String doctorCode;

	

	



}
