package com.traineeservice.bean;

import java.sql.Date;

import jakarta.validation.constraints.NotEmpty;
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
public class ClassSchedulingBean {
	private Long classId;

	private String name;

	private String className;

	private Date date;

	private Integer enrolled;

	private String duration;

	private Long trainerId;

}
