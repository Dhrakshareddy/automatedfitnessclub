package com.training.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
		
	private UserBean userBean;

	private WorkoutBean workoutBean;
	
	private ProgressBean progressBean;

}
