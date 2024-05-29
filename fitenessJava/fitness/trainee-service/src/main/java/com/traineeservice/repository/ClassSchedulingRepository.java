package com.traineeservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traineeservice.entity.ClassScheduling;

public interface ClassSchedulingRepository extends JpaRepository<ClassScheduling, Long> {

	List<ClassScheduling> findByDateBetween(Date currentDate, Date oneWeekLater);

	List<ClassScheduling> getClassesByName(String name);

}
