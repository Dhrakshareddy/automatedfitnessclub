package com.management.service;

import java.util.List;
import java.util.Optional;

import com.management.bean.MedicalHistoryBean;
import com.management.dto.AuthRequest;
import com.management.entity.User;

public interface UserService {

	User update(User user);

	User saveUser(User user);

	public List<MedicalHistoryBean> getMedicalHistoryBean(String username);

	User getUserById(Long userId);

	List<User> getAll();

	User deleteById(Long userId);

	User validateLogin(AuthRequest authRequest);

	List<User> getUsersByTrainerCode(String trainerCode);

	Optional<User> getUserByName(String name);

	User updatePassword(String email, String password);

		

}
