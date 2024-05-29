package com.management.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "equipment")
public class Equipment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "equipment_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long equipmentId;

	@Column(name = "equipment_name")
	private String equipmentName;

	@Column(name = "description")
	private String description;

	@Column(name = "brand")
	private String brand;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "purchase_date")
	private Date purchaseDate;

}
