package com.training.bean;

import com.training.entity.Exercise;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ExerciseBean {

	private Long exerciseId;
	
	private String exerciseName;

	private String description;

	

	private Integer numberOfSets;
	
	private String equipmentNeeded;
	private String username;

	
}
