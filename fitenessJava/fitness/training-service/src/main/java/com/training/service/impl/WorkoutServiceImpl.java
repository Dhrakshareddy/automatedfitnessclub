package com.training.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.training.bean.WorkoutBean;
import com.training.entity.Workout;
import com.training.exception.ResourceNotFoundException;
import com.training.repository.WorkoutRepository;
import com.training.service.WorkoutService;

@Service
public class WorkoutServiceImpl implements WorkoutService {

	@Autowired
	private WorkoutRepository workoutRepository;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Saves a workout entity.
	 * 
	 * @param workout The workout entity to save.
	 */

	@Override
	public void save(Workout workout) {
		workoutRepository.save(workout);
		System.out.println("Save success");
	}

	/**
	 * Maps a Workout entity to a WorkoutBean.
	 * 
	 * @param workout The Workout entity to map.
	 * @return The mapped WorkoutBean.
	 */

	private WorkoutBean mapToWorkout(Workout workout) {
		WorkoutBean workoutBean = new WorkoutBean();

		workoutBean.setUsername(workout.getUsername());

		workoutBean.setWorkoutDate(workout.getWorkoutDate());

		return workoutBean;

	}

	/**
	 * Updates a workout entity.
	 * 
	 * @param workout The workout entity to update.
	 * @throws ResourceNotFoundException if no workout is found with the given ID.
	 */

	@Override
	public void update(Workout workout) {
		Optional<Workout> optional = workoutRepository.findById(workout.getWorkoutId());
		if (optional.isPresent()) {
			workoutRepository.save(workout);

		} else {
			optional.orElseThrow(() -> new ResourceNotFoundException(
					"No Workout found to update for id : " + workout.getWorkoutId()));
		}

	}

	/**
	 * Retrieves workout entities by username.
	 * 
	 * @param username The username associated with the workout entities to
	 *                 retrieve.
	 * @return A list of workout entities associated with the specified username.
	 */

	@Override
	public List<Workout> getWorkoutByUsername(String username) {

		return workoutRepository.findByUsername(username);
	}

	/**
	 * Retrieves a workout entity by ID.
	 * 
	 * @param id The ID of the workout entity to retrieve.
	 * @return The retrieved workout entity.
	 * @throws ResourceNotFoundException if no workout is found with the given ID.
	 */

	@Override
	public Workout getById(Long id) {
		Optional<Workout> optional = workoutRepository.findById(id);
		if (optional.isPresent()) {
			System.out.println("Fetch success");
			return optional.get();
		} else {
			optional.orElseThrow(() -> new ResourceNotFoundException("No Workout found to fetch for id : " + id));
		}
		return null;
	}

	/**
	 * Retrieves all workout entities.
	 * 
	 * @return A list containing all workout entities.
	 */

	@Override
	public List<Workout> getAll() {
		return workoutRepository.findAll();
	}

	/**
	 * Deletes a workout entity by ID.
	 * 
	 * @param id The ID of the workout entity to delete.
	 * @return The deleted workout entity.
	 * @throws ResourceNotFoundException if no workout is found with the given ID.
	 */

	@Override
	public Workout delete(Long id) {
		Optional<Workout> optional = workoutRepository.findById(id);
		if (optional.isPresent()) {
			workoutRepository.deleteById(id);

			return optional.get();
		} else {
			optional.orElseThrow(() -> new ResourceNotFoundException("No Workout found to delete for id : " + id));
		}
		return null;
	}

}
