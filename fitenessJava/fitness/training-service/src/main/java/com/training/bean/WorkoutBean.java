package com.training.bean;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class WorkoutBean {

	private Long workoutId;
	
	private Date workoutDate;

	private String duration;

	private Long setsCompleted;
	
	private String  username;

	
}
