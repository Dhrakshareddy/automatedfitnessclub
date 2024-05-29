package com.healthservice.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class MedicalHistoryBean {

	private Long medicalHistoryId;

	private String dateOfAssessment;

	private String bloodPressure;

	private Long heartRate;

	private String allergies;

	private String previousInjuryOrSurgery;

	private Long fitnessLevel;

	private Long bodyMassIndex;

	public String username;
    
}
