package com.training.service;

import java.util.List;

import com.training.bean.WorkoutBean;
import com.training.entity.Workout;

public interface WorkoutService {

	void save(Workout workout);

	void update(Workout workout);

	Workout getById(Long id);

	List<Workout> getAll();

	Workout delete(Long id);


	  List<Workout> getWorkoutByUsername(String username);

	
	
}
