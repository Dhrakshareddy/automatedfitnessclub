package com.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.training.bean.ProgressBean;
import com.training.bean.UserBean;
import com.training.entity.Exercise;
import com.training.entity.Progress;
import com.training.entity.Workout;
import com.training.repository.ProgressRepository;
import com.training.service.ProgressService;
import com.training.service.WorkoutService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProgressServiceImpl implements ProgressService {

	@Autowired
	private ProgressRepository progressRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WorkoutService workoutService;

	/**
	 * Saves a progress entity.
	 * 
	 * @param progress The progress entity to save.
	 */
	@Override
	public void save(Progress progress) {
		String name = progress.getUsername();
		System.out.println(name);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ProgressBean progressBean = mapToProgress(progress);
		HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
		ResponseEntity<UserBean> responseEntity = restTemplate.exchange(
				"http://localhost:8082/api/user/getByName/" + progress.getUsername(), HttpMethod.GET, httpEntity,
				UserBean.class);
		UserBean userBean = responseEntity.getBody();
		progress.setUsername(userBean.getName());
		progressRepository.save(progress);
		log.info("Saved successfully");
	}

	/**
	 * Maps a Progress entity to a ProgressBean.
	 * 
	 * @param progress The Progress entity to map.
	 * @return The mapped ProgressBean.
	 */

	private ProgressBean mapToProgress(Progress progress) {
		ProgressBean progressBean = new ProgressBean();
		progressBean.setProgressId(progress.getProgressId());
		progressBean.setUsername(progress.getUsername());
		progressBean.setProgressDate(progress.getProgressDate());
		progressBean.setCalculateProgress(progress.getCalculateProgress());

		return progressBean;
	}

	/**
	 * Calculates the progress based on completed exercises and workouts.
	 * 
	 * @param exercises The list of exercises.
	 * @param workouts  The list of workouts.
	 * @return The calculated progress.
	 */

	@Override
	public double calculateProgress(List<Exercise> exercises, List<Workout> workouts) {
		double totalProgress = 0.0;
		int totalExercises = 0;

		for (Exercise exercise : exercises) {
			for (Workout workout : workouts) {

				if (exercise.getUsername().equals(workout.getUsername())) {
					if (exercise.getNumberOfSets() > 0) {
						double exerciseProgress = (double) workout.getSetsCompleted() / exercise.getNumberOfSets()
								* 100.0;
						totalProgress += exerciseProgress;
						totalExercises++;
					}
				}
			}
		}

		double averageProgress = (totalExercises > 0) ? totalProgress / totalExercises : 0.0;

		return averageProgress;
	}

	/**
	 * Retrieves progress entities by username.
	 * 
	 * @param username The username associated with the progress entities to
	 *                 retrieve.
	 * @return A list of progress entities associated with the specified username.
	 */

	@Override
	public List<Progress> getProgressByUsername(String username) {

		return progressRepository.findByUsername(username);
	}

}
