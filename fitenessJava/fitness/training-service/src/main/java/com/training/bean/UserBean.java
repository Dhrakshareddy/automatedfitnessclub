package com.training.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserBean {
	private Long userId;
	private String name;
	private String password;
	private String dateOfBirth;
	private String role;
	private String fitnessActivity;
	private String email;
	private Long contactNumber;
	
}
