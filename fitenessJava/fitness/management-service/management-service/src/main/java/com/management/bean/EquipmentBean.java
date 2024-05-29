package com.management.bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EquipmentBean {
	private Long equipmentId;
	
	private String equipmentName;

	private String description;

	private String brand;

	private Integer quantity;

	private Date purchaseDate;

}

