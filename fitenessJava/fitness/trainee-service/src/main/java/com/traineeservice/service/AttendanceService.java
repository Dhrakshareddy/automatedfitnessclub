package com.traineeservice.service;

import java.util.List;

import com.traineeservice.bean.ResponseDto;
import com.traineeservice.entity.Attendance;

public interface AttendanceService {

	List<Attendance> getAll();

	String deleteById(Long attendanceId);

	void deleteAll();

	Attendance update(Attendance attendance);

	ResponseDto getAttendance(Long attendanceId);

	Attendance saveAttendance(Attendance attendance);

	List<Attendance> getAttendanceByName(String name);

}
