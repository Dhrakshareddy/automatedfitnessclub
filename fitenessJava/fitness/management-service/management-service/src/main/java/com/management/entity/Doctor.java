package com.management.entity;

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
@Table(name = "doctor_profile")
public class Doctor {
	
	private static final Long serialVersionUID = -1516965327693370237L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doctor_id")
	private Long doctorId;
	
	@Column(name = "doctor_name")
	private String name;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "age")
	private String age;

	@Column(name = "year_of_experience")
	private Integer yearOfExperience;
	
	@Column(name = "contact_no")
	private String contactNumber;
	
	@Column(name = "shift_timings")
	private String shiftTimings;
	
	@Column(name = "specialization")
	private String specialization;

	@Column(name = "role")
	private String role;

	@Column(name = "doctor_code")
	private String doctorCode;


}
