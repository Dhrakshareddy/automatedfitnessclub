package com.healthservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "medicalhistory")
public class MedicalHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long medicalHistoryId;

	@Column(name = "date_of_assessment")
	private String dateOfAssessment;

	@Column(name = "blood_presure")
	private String bloodPressure;

	@Column(name = "heart_rate")
	private Long heartRate;

	@Column(name = "allergies")
	private String allergies;

	@Column(name = "previous_injury_surgery")
	private String previousInjuryOrSurgery;

	@Column(name = "fitness_level")
	private Long fitnessLevel;

	@Column(name = "body_mass_index")
	private Long bodyMassIndex;

	@Column(name = "user_name")
	public String username;

}
