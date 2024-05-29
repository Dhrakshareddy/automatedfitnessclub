package com.management.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.management.bean.MedicalHistoryBean;
import com.management.constants.ManagementConstants;
import com.management.dto.AuthRequest;
import com.management.entity.User;
import com.management.exception.EmailNotFoundException;
import com.management.exception.PasswordMismatchException;
import com.management.exception.ResourceNotFoundException;
import com.management.repository.UserRepository;
import com.management.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	
	
	
	

	/**
	 * Saves a user entity.
	 * 
	 * @param user The user entity to save.
	 * @return The saved user entity.
	 */

	@Override
	public User saveUser(User user) {
		user.setRole(ManagementConstants.USERROLE);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	/**
	 * Retrieves a user entity by ID.
	 * 
	 * @param id The ID of the user entity to retrieve.
	 * @return The retrieved user entity.
	 * @throws ResourceNotFoundException If no user is found with the given ID.
	 */

	@Override
	public User getUserById(Long id) {
		Optional<User> optional = userRepository.findById(id);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(
						() -> new ResourceNotFoundException("No user was found to fetch for the given ID:" + id));
			} catch (ResourceNotFoundException e) {
				log.error(e.getMessage());
				throw e;
			}
		}
		log.info("Fetched successfully");
		return optional.get();
	}

	/**
	 * Retrieves all user entities.
	 * 
	 * @return A list of all user entities.
	 */

	@Override
	public List<User> getAll() {
		log.info("Fetching all users");
		return userRepository.findAll();
	}

	/**
	 * Retrieves medical history data for a user by username.
	 * 
	 * @param username The username of the user.
	 * @return A list of medical history data for the user.
	 */

	@Override
	public List<MedicalHistoryBean> getMedicalHistoryBean(String username) {
		String url = "http://localhost:8086/medicalHistory/fetchbyName/{username}";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);

		ResponseEntity<List<MedicalHistoryBean>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<List<MedicalHistoryBean>>() {
				}, username);

		return responseEntity.getBody();
	}

	/**
	 * Validates the login credentials of a user.
	 * 
	 * @param loginBean The bean containing user login details.
	 * @return The authenticated user entity.
	 */

		 @Override
	    public User validateLogin(AuthRequest authRequest) {
	        Optional<User> user = userRepository.findByName(authRequest.getName());
	        User details=user.get();
	        log.info("login by using email and password");

	        if (details != null) {
	            log.info("email is valid");

	            if (details.getPassword().equals(authRequest.getPassword())) {
	                log.info("login successful");
	                return details;
	            } else {
	                try {
	                    log.info("login failed");
	                    throw new PasswordMismatchException("Password is wrong");
	                } catch (PasswordMismatchException exception) {
	                    log.error("password is mismatch");
	                }
	            }
	        } else {
	            throw new EmailNotFoundException("Email not found");
	        }
	        System.out.println("user is not found");
	        return details;
	    }


	/**
	 * Retrieves a user entity by name.
	 * 
	 * @param name The name of the user.
	 * @return The retrieved user entity.
	 */

//	@Override
//	public Optional<User> getUserByName(String name) {
//		return userRepository.findByName(name);
//	}

	/**
	 * Updates a user entity.
	 * 
	 * @param user The user entity to update.
	 * @return The updated user entity.
	 * @throws ResourceNotFoundException If no user is found with the given ID.
	 */
	@Override
	public User update(User user) {
		log.info("Updating user: {}", user);
		Optional<User> optional = userRepository.findById(user.getUserId());
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new ResourceNotFoundException(
						"No user was found to update for the given ID:" + user.getUserId()));
			} catch (ResourceNotFoundException e) {
				log.error(e.getMessage());
				throw e;
			}
		}
		userRepository.save(user);
		log.info("Updated successfully");
		return user;
	}

	/**
	 * Retrieves users associated with a trainer code.
	 * 
	 * @param trainerCode The trainer code.
	 * @return A list of users associated with the trainer code.
	 */
	@Override
	public List<User> getUsersByTrainerCode(String trainerCode) {
		Optional<List<User>> optional = userRepository.findByTrainerCode(trainerCode);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new ResourceNotFoundException(
						"No user was found to fetch for the given ID:" + trainerCode));
			} catch (ResourceNotFoundException e) {
				log.error(e.getMessage());
				throw e;
			}
		}
		log.info("Fetched successfully");
		return optional.get();
	}

	/**
	 * Updates the password for a user.
	 * 
	 * @param email    The email of the user.
	 * @param password The new password.
	 * @return The user entity with the updated password.
	 */

	@Override
	public User updatePassword(String email, String password) {
		User user = userRepository.findByEmail(email);
		if (user != null) {
			user.setPassword(password);
			userRepository.save(user);
		}
		return user;
	}

	/**
	 * Deletes a user entity by ID.
	 * 
	 * @param userId The ID of the user entity to delete.
	 * @return The deleted user entity.
	 * @throws ResourceNotFoundException If no user is found with the given ID.
	 */

	@Override
	public User deleteById(Long userId) {
		Optional<User> optional = userRepository.findById(userId);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(
						() -> new ResourceNotFoundException("No user was found to delete for the given ID:" + userId));
			} catch (ResourceNotFoundException e) {
				log.error(e.getMessage());
				throw e;
			}
		}
		userRepository.deleteById(userId);
		log.info("Deleted successfully");
		return optional.get();
	}

	@Override
	public Optional<User> getUserByName(String name) {
		// TODO Auto-generated method stub
		return userRepository.findByName(name);
	}
	
	
	



	

	
		
}
