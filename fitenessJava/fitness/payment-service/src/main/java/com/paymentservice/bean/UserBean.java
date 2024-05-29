package com.paymentservice.bean;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
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
