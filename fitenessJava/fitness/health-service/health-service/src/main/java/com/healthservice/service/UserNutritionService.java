package com.healthservice.service;

import java.util.List;
import java.util.Optional;

import com.healthservice.bean.ResponseDto;
import com.healthservice.bean.UserBean;
import com.healthservice.entity.Nutrition;

public interface UserNutritionService {

	Nutrition saveNewNutrition(Nutrition bean);

	ResponseDto getById(Integer nutritionId);

	List<Nutrition> getAllNutrition();

	public Nutrition updateNutrition(Integer nutritionId, Nutrition updatedNutrition);

	void deleteById(Integer nutritionId);

	public List<UserBean> getUserBean();

	List<Nutrition> getByUsername(String username);


}
