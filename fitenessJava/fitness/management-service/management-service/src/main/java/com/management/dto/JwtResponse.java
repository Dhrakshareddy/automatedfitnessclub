package com.management.dto;

import com.management.entity.Admin;
import com.management.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {

	private String accessToken;
	private String token;
	private User user;
	
}
