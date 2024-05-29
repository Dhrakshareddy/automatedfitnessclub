package com.traineeservice.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class TrainerBean {
	private Long trainerId;

	private String name;

	private String age;

	private Integer yearOfExperience;

	private String shiftTimings;

	private String certification;

	private String contactNumber;

}
