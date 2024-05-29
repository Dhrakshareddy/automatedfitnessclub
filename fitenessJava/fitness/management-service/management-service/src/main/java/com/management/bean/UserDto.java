package com.management.bean;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
	private Long userId;
	
	private String name;
	
	private String password;
	
	private Date dateOfBirth;
	
	private String email;
	
	private Long contactNumber;
	
	private String fitnessActitvity;
	
	private String role;

}
