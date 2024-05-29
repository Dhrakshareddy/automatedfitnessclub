package com.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.bean.WorkoutBean;
import com.training.entity.Workout;

public interface WorkoutRepository extends JpaRepository<Workout, Long>{

	//Optional<List<Workout>> findByUsername(String name);

	
	List<Workout> findByUsername(String username);
}
