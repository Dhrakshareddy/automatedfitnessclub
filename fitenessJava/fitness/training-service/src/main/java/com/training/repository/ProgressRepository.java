package com.training.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.training.entity.Progress;
import com.training.entity.Workout;

public interface ProgressRepository extends JpaRepository<Progress, Long>{
	 @Query("SELECT p FROM Progress p WHERE p.username = :username")
	List<Progress> findByUsername(@Param("username") String username);

	
	
	

}
