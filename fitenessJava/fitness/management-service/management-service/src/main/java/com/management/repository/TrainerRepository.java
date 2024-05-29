package com.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.management.entity.Trainer;

@EnableJpaRepositories
public interface TrainerRepository extends JpaRepository<Trainer, Long> {

	Optional<Trainer> findById(Long trainerId);

	Trainer findByName(String name);

	Optional<Trainer> findByTrainerCode(String trainerCode);
}
