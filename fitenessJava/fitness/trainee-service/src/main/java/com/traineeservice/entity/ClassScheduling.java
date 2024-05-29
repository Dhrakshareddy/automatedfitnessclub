package com.traineeservice.entity;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "class_scheduling")
public class ClassScheduling implements Serializable 
{
private static final long serialVersionUID = -1516965327693370237L;

@Id
@Column(name = "class_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long classId;

private String name;

@Column(name = "class_name")
private String className;

@Column(name = "date")
private Date date;

@Column(name = "enrolled")
private Integer enrolled;

@Column(name = "duration")
private String duration;

@Column(name="trainer_id")
private Long trainerId;

}
