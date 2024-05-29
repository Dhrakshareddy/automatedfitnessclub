package com.healthservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthservice.entity.MedicalHistory;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {
	
	List<MedicalHistory> findByUsername(String username);
}
