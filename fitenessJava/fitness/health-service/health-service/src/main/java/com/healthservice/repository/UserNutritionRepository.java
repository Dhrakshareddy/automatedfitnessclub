package com.healthservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthservice.entity.Nutrition;

public interface UserNutritionRepository extends JpaRepository<Nutrition, Integer> {

  Optional<List<Nutrition>> findByUsername(String username);

}
