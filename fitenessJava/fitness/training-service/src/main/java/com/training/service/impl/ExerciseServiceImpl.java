package com.training.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.training.bean.ExerciseBean;
import com.training.bean.UserBean;
import com.training.entity.Exercise;
import com.training.exception.ResourceNotFoundException;
import com.training.repository.ExerciseRepository;
import com.training.service.ExerciseService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExerciseServiceImpl implements ExerciseService {

	@Autowired
	private ExerciseRepository exerciseRepository;
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Updates an exercise entity.
	 * 
	 * @param exercise The exercise entity to update.
	 * @throws ResourceNotFoundException if no exercise is found with the given ID.
	 */

	@Override
	public void update(Exercise exercise) {
		Optional<Exercise> optional = exerciseRepository.findById(exercise.getExerciseId());
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new ResourceNotFoundException(
						"No Exercise found to update for id : " + exercise.getExerciseId()));
			} catch (ResourceNotFoundException e) {
				throw e;
			}
		}

		log.info("Updated successfully");
		exerciseRepository.save(exercise);

	}

	/**
	 * Retrieves an exercise entity by ID.
	 * 
	 * @param id The ID of the exercise entity to retrieve.
	 * @return The retrieved exercise entity.
	 * @throws ResourceNotFoundException if no exercise is found with the given ID.
	 */

	@Override
	public Exercise getById(Long id) {
		Optional<Exercise> optional = exerciseRepository.findById(id);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new ResourceNotFoundException("No Exercise found to fetch for id : " + id));
			} catch (ResourceNotFoundException e) {
				throw e;
			}
		}
		log.info("Fetched successfully");

		return optional.get();
	}

	/**
	 * Retrieves a list of all exercise entities.
	 * 
	 * @return A list containing all exercise entities.
	 */
	@Override
	public List<Exercise> getAll() {
		return exerciseRepository.findAll();
	}

	/**
	 * Deletes an exercise entity by ID.
	 * 
	 * @param id The ID of the exercise entity to delete.
	 * @return The deleted exercise entity.
	 * @throws ResourceNotFoundException if no exercise is found with the given ID.
	 */

	@Override
	public Exercise delete(Long id) {
		Optional<Exercise> optional = exerciseRepository.findById(id);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new ResourceNotFoundException("No Exercise found to delete for id : " + id));
			} catch (ResourceNotFoundException e) {
				throw e;
			}
		}
		exerciseRepository.deleteById(id);
		log.info("Deleted successfully");
		return optional.get();
	}

	/**
	 * Maps an Exercise entity to an ExerciseBean.
	 * 
	 * @param exercise The Exercise entity to map.
	 * @return The mapped ExerciseBean.
	 */

	private ExerciseBean mapToExercise(Exercise exercise) {
		ExerciseBean exerciseBean = new ExerciseBean();
		exerciseBean.setExerciseName(exercise.getExerciseName());
		exerciseBean.setDescription(exercise.getDescription());
		exerciseBean.setNumberOfSets(exercise.getNumberOfSets());
		exerciseBean.setEquipmentNeeded(exercise.getEquipmentNeeded());

		return exerciseBean;
	}

	/**
	 * Retrieves exercise entities by username.
	 * 
	 * @param username The username associated with the exercise entities to
	 *                 retrieve.
	 * @return A list of exercise entities associated with the specified username.
	 */

	@Override
	public List<Exercise> getExerciseByUsername(String username) {

		return exerciseRepository.findByUsername(username);
	}

	/**
	 * Saves an exercise entity.
	 * 
	 * @param exercise The exercise entity to save.
	 */
	public void save(Exercise exercise) {
		String name = exercise.getUsername();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ExerciseBean workoutBean = mapToExercise(exercise);
		HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
		ResponseEntity<UserBean> responseEntity = restTemplate.exchange(
				"http://localhost:8082/api/user/getByName/" + exercise.getUsername(), HttpMethod.GET, httpEntity,
				UserBean.class);
		UserBean userBean = responseEntity.getBody();
		System.out.println(userBean);
		exercise.setUsername(userBean.getName());
		exerciseRepository.save(exercise);
		log.info("Saved successfully");

	}

}
