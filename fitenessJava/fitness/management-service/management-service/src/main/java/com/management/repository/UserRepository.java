package com.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.management.entity.User;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {


	Optional<User> findByName(String name);


	List<User> findByDoctorCode(String doctorCode);

	Optional<List<User>> findByTrainerCode(String trainerCode);

	User findByEmail(String email);


	

}
