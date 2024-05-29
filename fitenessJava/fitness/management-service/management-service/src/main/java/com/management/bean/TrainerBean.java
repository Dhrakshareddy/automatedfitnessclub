package com.management.bean;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TrainerBean {
	private Long trainerId;
	
	private String name;
	
	private String password;
	
	private String age;
	
	private Integer yearOfExperience;
	
	private String shiftTimings;
	
	private String role;
	
	private String contactNumber;
	
	private String trainerCode;

}
