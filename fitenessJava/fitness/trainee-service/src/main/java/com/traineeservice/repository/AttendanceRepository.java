package com.traineeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traineeservice.bean.UserBean;
import com.traineeservice.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	List<Attendance> findByName(String name);

}
