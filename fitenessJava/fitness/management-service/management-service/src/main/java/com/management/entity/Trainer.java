package com.management.entity;

import java.io.Serializable;

import org.hibernate.annotations.GenericGenerator;

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
@Table(name = "trainer_profile")
public class Trainer implements Serializable {
	
	private static final Long serialVersionUID = -1516965327693370237L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long trainerId;

	@Column(unique = true, name = "trainer_name")
	private String name;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "age")
	private String age;
	
	@Column(name = "year_of_experience")
	private Integer yearOfExperience;
	
	@Column(name = "shift_timings")
	private String shiftTimings;

	@Column(name = "contact_No")
	private String contactNumber;
	
	@Column(name = "role")
	private String role;

	@Column(name = "trainer_code")
	private String trainerCode;


}
