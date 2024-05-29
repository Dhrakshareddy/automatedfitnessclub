package com.management.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DoctorBean {
	private Long doctorId;

	private String name;

	private String password;

	private String age;

	private Integer yearOfExperience;

	private String contactNumber;

	private String shiftTimings;

	private String specialization;

	private String role;

	private String doctorCode;

}
