package com.healthservice.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nutrition")
public class Nutrition {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer nutritionId;

	@Column(name = "food_item")
	private String foodItem;

	@Column(name = "calories_consumed")
	private String caloriesToBeconsumed;

	@Column(name = "protein_consumed")
	private String proteinToBeconsumed;

	@Column(name = "carbohydrates_consumed")
	private String carbohydratesToBeconsumed;

	@Column(name = "user_name")
	public String username;

}
