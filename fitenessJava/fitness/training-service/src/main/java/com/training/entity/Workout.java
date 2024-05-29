package com.training.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name="workout")
public class Workout implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Column(name="workout_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long workoutId;
	
	@Column(name="workout_date")
	private Date workoutDate;
	
	@Column(name="duration")
	private String duration;
	
	
	@Column(name="sets_Completed")
	private Long setsCompleted;
	
	@Column(name="username")
	private String username;
	
	

}