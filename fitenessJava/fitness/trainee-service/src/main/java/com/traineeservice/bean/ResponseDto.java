package com.traineeservice.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {

	private UserBean userBean;

	private AttendanceBean attendanceBean;

	private ClassSchedulingBean classSchedulingBean;

	private TrainerBean trainerBean;

}
