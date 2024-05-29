package com.traineeservice.bean;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AttendanceBean {
	
	private Long attendanceId;

	private String name;

	private Date date;

	private String status;

	private String feedback;

	private Long userId;
}
