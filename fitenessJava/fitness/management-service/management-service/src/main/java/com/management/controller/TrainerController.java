package com.management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.management.bean.TrainerLoginBean;
import com.management.entity.Trainer;
import com.management.service.TrainerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/trainer")
@CrossOrigin(origins = "http://localhost:4200")
public class TrainerController {

	private static final Logger log = LoggerFactory.getLogger(TrainerController.class);
/**
 * 
 */
	@Autowired
	private TrainerService trainerService;

	/**
	 * 
	 * @param trainer
	 * @return
	 */
	@PostMapping("/add")
	public ResponseEntity<Trainer> save(@RequestBody Trainer trainer) {
		trainerService.save(trainer);
		log.info("Trainer saved successfully: {}", trainer);
		ResponseEntity<Trainer> responseEntity = new ResponseEntity<>(trainer, HttpStatus.CREATED);
		return responseEntity;
	}

	/**
	 * 
	 * @param trainerId
	 * @return
	 */
	
	@GetMapping("/getById/{trainerId}")
	public ResponseEntity<Trainer> get(@PathVariable Long trainerId) {
		log.info("Fetching trainer by ID: {}", trainerId);
		Trainer trainer = trainerService.get(trainerId);
		ResponseEntity<Trainer> responseEntity = new ResponseEntity<>(trainer, HttpStatus.OK);
		return responseEntity;
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/getAll")
	public ResponseEntity<List<Trainer>> getAll() {
		log.info("Fetching all trainers");
		List<Trainer> trainers = trainerService.getAll();
		ResponseEntity<List<Trainer>> responseEntity = new ResponseEntity<>(trainers, HttpStatus.OK);
		return responseEntity;
	}

	/**
	 * 
	 * @param trainerId
	 * @return
	 */
	@DeleteMapping("/deleteById/{trainerId}")
	public String deleteById(@PathVariable Long trainerId) {
		log.info("Deleting trainer by ID: {}", trainerId);
		trainerService.deleteById(trainerId);
		log.info("Trainer deleted successfully");
		return "Deleted successfully";
	}

	/**
	 * 
	 * @param trainer
	 * @return
	 */
	@PutMapping("/updateById")
	public Trainer update(@RequestBody Trainer trainer) {
		log.info("Updating trainer by ID: {}", trainer.getTrainerId());
		Trainer update = trainerService.update(trainer);
		log.info("Trainer updated successfully: {}", update);
		return update;
	}

	/**
	 * 
	 * @param trainerLoginBean
	 * @return
	 */
	
	@PostMapping("/login")
	public ResponseEntity<Trainer> login(@RequestBody TrainerLoginBean trainerLoginBean) {
		Trainer trainer = trainerService.validateLogin(trainerLoginBean);

		if (trainer != null) {
			log.info("Trainer login successful: {}", trainer);
			return ResponseEntity.ok(trainer);
		} else {
			log.warn("Trainer login failed");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}

	/**
	 * 
	 * @param trainerCode
	 * @return
	 */
	@GetMapping("/by-trainer-code/{trainerCode}")
	public Trainer getTrainerByTrainerCode(@PathVariable String trainerCode) {
		return trainerService.findByTrainerCode(trainerCode);
	}

	/**
	 * 
	 * @param trainerCode
	 * @return
	 */
	@GetMapping("/getByCode/{trainerCode}")
	public ResponseEntity<Trainer> getByTrainerCode(@PathVariable String trainerCode) {
		log.info("Fetching trainer by trainer code: {}", trainerCode);
		Trainer trainer = trainerService.getByTrainerCode(trainerCode);
		ResponseEntity<Trainer> responseEntity = new ResponseEntity<>(trainer, HttpStatus.OK);
		return responseEntity;
	}
}
