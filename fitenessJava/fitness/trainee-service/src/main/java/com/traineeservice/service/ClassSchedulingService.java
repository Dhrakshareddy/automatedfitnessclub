package com.traineeservice.service;

import java.util.List;

import com.traineeservice.bean.ResponseDto;
import com.traineeservice.entity.ClassScheduling;

public interface ClassSchedulingService {

	List<ClassScheduling> getAll();

	String deleteById(Long id);

	void deleteAll();

	ClassScheduling update(ClassScheduling scheduling);

	ResponseDto getClassscheduling(Long classId);

	ClassScheduling saveclClassScheduling(ClassScheduling classScheduling);

	List<ClassScheduling> getOneWeekClassScheduling();

	List<ClassScheduling> getClassesByName(String name);
}
