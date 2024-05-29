package com.management.service;

import java.util.List;

import com.management.bean.TrainerBean;
import com.management.bean.TrainerLoginBean;
import com.management.entity.Trainer;
import com.management.entity.User;

public interface TrainerService {

	void save(Trainer trainer);

	List<Trainer> getAll();

	Trainer update(Trainer trainer);

	Trainer validateLogin(TrainerLoginBean trainerLoginBean);

	Trainer deleteById(Long trainerId);

	Trainer get(Long trainerId);

	Trainer findByTrainerCode(String trainerCode);

	Trainer getByTrainerCode(String trainerCode);

}
