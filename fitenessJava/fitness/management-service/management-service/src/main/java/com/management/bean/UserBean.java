package com.management.bean;

import java.util.Date;

import jakarta.persistence.Column;
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
public class UserBean {
	private Long userId;
	
	private String name;
	
	private String password;
	
	private Date dateOfBirth;
	
	private String email;
	
	private Long contactNumber;
	
	private String role;
	
	private String fitnessActivity;
	
	private String trainerCode;
	
	private String doctorCode;

}
