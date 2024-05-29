package com.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.entity.Exercise;
import com.training.entity.Workout;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
	List<Exercise> findByUsername(String username);

}
