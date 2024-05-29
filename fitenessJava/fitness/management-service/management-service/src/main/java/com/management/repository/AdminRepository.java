package com.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.management.entity.Admin;

@EnableJpaRepositories
public interface AdminRepository  extends JpaRepository<Admin, Long>{

	
	Admin findByName(String name);

}
