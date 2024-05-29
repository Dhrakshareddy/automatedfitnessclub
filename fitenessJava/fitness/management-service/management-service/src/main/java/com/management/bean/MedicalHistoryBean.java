package com.management.bean;

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

	private Long memberId;
	
	private String dateOfAssessment;
	
	private int bloodPressure;
	
	private int heartRate;
	
	private String allergies;
	
	private String medications;
	
	private String chronicCondition;
	
	private String familyMedicalHistory;
	
	private String previousInjuryOrSurgery;
	
	private int fitnessLevel;
	
	private Integer physicianContactNumber;

}