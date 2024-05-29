package com.training.service;

import java.util.List;

import com.training.entity.Exercise;
import com.training.entity.Progress;
import com.training.entity.Workout;

public interface ProgressService {

	void save(Progress progress);

	

	double calculateProgress(List<Exercise> exercises, List<Workout> workouts);

	List<Progress> getProgressByUsername(String username);
	

}
