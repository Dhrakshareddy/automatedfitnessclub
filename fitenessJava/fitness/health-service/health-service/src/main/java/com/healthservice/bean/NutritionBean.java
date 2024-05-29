package com.healthservice.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class NutritionBean {

	private Integer nutritionId;

	private String foodItem;

	private String caloriesToBeconsumed;

	private String proteinToBeconsumed;

	private String carbohydratesToBeconsumed;

	public Long userId;

	public String username;

}
