package com.management.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.bean.TrainerLoginBean;
import com.management.constants.ManagementConstants;
import com.management.entity.Trainer;
import com.management.exception.PasswordMismatchException;
import com.management.exception.ResourceNotFoundException;
import com.management.repository.TrainerRepository;
import com.management.service.TrainerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TrainerServiceImpl implements TrainerService {

	@Autowired
	private TrainerRepository trainerRepository;

	/**
	 * Saves a trainer entity.
	 * 
	 * @param trainer The trainer entity to save.
	 */
	@Override
	public void save(Trainer trainer) {
		trainer.setRole(ManagementConstants.TRAINERROLE);
		trainerRepository.save(trainer);
		log.info("Saved successfully");
	}

	/**
	 * Retrieves a trainer entity by ID.
	 * 
	 * @param trainerId The ID of the trainer entity to retrieve.
	 * @return The retrieved trainer entity.
	 * @throws ResourceNotFoundException If no trainer is found with the given ID.
	 */
	@Override
	public Trainer get(Long trainerId) {
		Optional<Trainer> optional = trainerRepository.findById(trainerId);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new ResourceNotFoundException(
						"No trainer was found to fetch for the given ID:" + trainerId));
			} catch (ResourceNotFoundException e) {
				log.error(e.getMessage());
				throw e;
			}
		}
		log.info("Fetched successfully");
		return optional.get();
	}

	/**
	 * Retrieves all trainer entities.
	 * 
	 * @return A list of all trainer entities.
	 */
	@Override
	public List<Trainer> getAll() {
		log.info("Fetching all trainers");
		return trainerRepository.findAll();
	}

	/**
	 * Deletes a trainer entity by ID.
	 * 
	 * @param trainerId The ID of the trainer entity to delete.
	 * @return The deleted trainer entity.
	 * @throws ResourceNotFoundException If no trainer is found with the given ID.
	 */

	@Override
	public Trainer deleteById(Long trainerId) {
		Optional<Trainer> optional = trainerRepository.findById(trainerId);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new ResourceNotFoundException(
						"No trainer was found to delete for the given ID:" + trainerId));
			} catch (ResourceNotFoundException e) {
				log.error(e.getMessage());
				throw e;
			}
		}
		trainerRepository.deleteById(trainerId);
		log.info("Deleted successfully");
		return optional.get();
	}

	/**
	 * Updates a trainer entity.
	 * 
	 * @param trainer The trainer entity to update.
	 * @return The updated trainer entity.
	 * @throws ResourceNotFoundException If no trainer is found with the given ID.
	 */

	@Override
	public Trainer update(Trainer trainer) {
		Optional<Trainer> optional = trainerRepository.findById(trainer.getTrainerId());
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new ResourceNotFoundException(
						"No trainer was found to update for the given ID:" + trainer.getTrainerId()));
			} catch (ResourceNotFoundException e) {
				log.error(e.getMessage());
				throw e;
			}
		}
		trainerRepository.save(trainer);
		log.info("Updated successfully");
		return trainer;
	}

	/**
	 * Validates the login credentials of a trainer.
	 * 
	 * @param trainerLoginBean The bean containing trainer login details.
	 * @return The authenticated trainer entity.
	 */

	@Override
	public Trainer validateLogin(TrainerLoginBean trainerLoginBean) {
		Trainer trainer = trainerRepository.findByName(trainerLoginBean.getName());
		log.info("Trainer found: {}", trainer);

		if (trainer != null) {
			Trainer registrationBean = new Trainer();

			if (trainer.getPassword().equals(trainerLoginBean.getPassword())) {
				log.info("Login successful");
				return trainer;
			} else {
				try {
					throw new PasswordMismatchException("Incorrect password");
				} catch (Exception e) {
					log.error(e.getMessage());
				}
				return trainer;
			}
		} else {
			try {
				throw new UserNameNotFoundException("Incorrect EmailId");
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return trainer;
	}

	/**
	 * Finds a trainer entity by trainer code.
	 * 
	 * @param trainerCode The code of the trainer to find.
	 * @return The found trainer entity.
	 */

	@Override
	public Trainer findByTrainerCode(String trainerCode) {
		Optional<Trainer> optional = trainerRepository.findByTrainerCode(trainerCode);
		if (optional.isPresent()) {
			Trainer trainer = optional.get();
			log.info("Trainer found by code: {}", trainer);
			return trainer;
		} else {
			log.info("Trainer not found by code: {}", trainerCode);
			return null;
		}
	}

	/**
	 * Retrieves a trainer entity by trainer code.
	 * 
	 * @param trainerCode The code of the trainer to retrieve.
	 * @return The retrieved trainer entity.
	 * @throws ResourceNotFoundException If no trainer is found with the given code.
	 */

	@Override
	public Trainer getByTrainerCode(String trainerCode) {
		Optional<Trainer> optional = trainerRepository.findByTrainerCode(trainerCode);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new ResourceNotFoundException(
						"No trainer was found to fetch for the given ID:" + trainerCode));
			} catch (ResourceNotFoundException e) {
				log.error(e.getMessage());
				throw e;
			}
		}
		log.info("Fetched successfully");
		return optional.get();
	}
}
