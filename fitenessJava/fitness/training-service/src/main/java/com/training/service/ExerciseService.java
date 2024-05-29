package com.training.service;

import java.util.List;

import com.training.bean.UserBean;
import com.training.entity.Exercise;
import com.training.entity.Workout;

public interface ExerciseService {

	void save(Exercise exercise);

	void update(Exercise exercise);
	
	Exercise getById(Long id);
	
	List<Exercise> getAll();

	Exercise delete(Long id);
	 List<Exercise> getExerciseByUsername(String username);

}
