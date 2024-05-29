package com.healthservice.bean;

import lombok.Data;

@Data
public class ResponseDto {
	private MedicalHistoryBean medicalHistory;

	private UserBean userBean;

	private NutritionBean nutritionbean;

}
