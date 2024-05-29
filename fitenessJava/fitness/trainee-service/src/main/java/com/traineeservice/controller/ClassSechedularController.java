package com.traineeservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traineeservice.bean.ResponseDto;
import com.traineeservice.entity.ClassScheduling;
import com.traineeservice.service.ClassSchedulingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "classSchedular")
@CrossOrigin(origins = "http://localhost:4200")
public class ClassSechedularController {

	private static final Logger log = LoggerFactory.getLogger(ClassSechedularController.class);

	@Autowired
	private ClassSchedulingService classSchedulingService;

	/**
	 * Retrieves class scheduling entries for the next week.
	 *
	 * @return A ResponseEntity containing a list of class scheduling entries for
	 *         the next week.
	 */
	@GetMapping("/weekly")
	public ResponseEntity<List<ClassScheduling>> getOneWeekClassScheduling() {
		log.info("Fetching one week class scheduling");
		List<ClassScheduling> oneWeekClassScheduling = classSchedulingService.getOneWeekClassScheduling();
		log.info("Fetched one week class scheduling successfully");
		return ResponseEntity.ok(oneWeekClassScheduling);
	}

	/**
	 * Retrieves class scheduling details by ID.
	 *
	 * @param classId The ID of the class scheduling entry to retrieve.
	 * @return A ResponseEntity containing class scheduling details and trainer
	 *         information.
	 */

	@GetMapping(path = "/{id}")
	public ResponseEntity<ResponseDto> getClassScheduling(@PathVariable("id") Long classId) {
		log.info("Fetching class scheduling by ID: {}", classId);
		ResponseDto responseDto = classSchedulingService.getClassscheduling(classId);
		log.info("Fetched class scheduling successfully");
		return ResponseEntity.ok(responseDto);
	}

	/**
	 * Saves a new class scheduling entry.
	 *
	 * @param classScheduling The class scheduling entry to save.
	 * @return A ResponseEntity containing the saved class scheduling entry.
	 */

	@PostMapping(path = "/save")
	public ResponseEntity<ClassScheduling> saveAchievement(@RequestBody ClassScheduling classScheduling) {
		log.info("Saving class scheduling: {}", classScheduling);
		ClassScheduling classScheduling2 = classSchedulingService.saveclClassScheduling(classScheduling);
		log.info("Class scheduling saved successfully");

		ResponseEntity<ClassScheduling> responseEntity = new ResponseEntity<>(classScheduling2, HttpStatus.CREATED);
		return responseEntity;
	}

	/**
	 * Retrieves class scheduling entries by name.
	 *
	 * @param name The name to search for.
	 * @return A ResponseEntity containing a list of class scheduling entries with
	 *         the specified name.
	 */

	@GetMapping("/byName/{name}")
	public ResponseEntity<List<ClassScheduling>> getClassesByName(@PathVariable String name) {
		log.info("Fetching classes by name: {}", name);
		List<ClassScheduling> classList = classSchedulingService.getClassesByName(name);
		if (classList.isEmpty()) {
			log.info("No classes found for name: {}", name);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		log.info("Fetched classes by name successfully");
		return new ResponseEntity<>(classList, HttpStatus.OK);
	}

	/**
	 * Retrieves all class scheduling entries.
	 *
	 * @return A ResponseEntity containing a list of all class scheduling entries.
	 */

	@GetMapping(path = "/getAll")
	public ResponseEntity<List<ClassScheduling>> getAll() {
		log.info("Getting all schedules");
		List<ClassScheduling> scheduling = classSchedulingService.getAll();
		log.info("Fetched all schedules successfully");
		ResponseEntity<List<ClassScheduling>> responseEntity = new ResponseEntity<>(scheduling, HttpStatus.OK);
		return responseEntity;
	}

	/**
	 * Deletes a class scheduling entry by ID.
	 *
	 * @param id The ID of the class scheduling entry to delete.
	 * @return A message indicating the deletion operation's success.
	 */

	@DeleteMapping(path = "/deleteById/{id}")
	public String deleteById(@PathVariable Long id) {
		log.info("Deleting schedule with ID: {}", id);
		classSchedulingService.deleteById(id);
		log.info("Deleted schedule successfully");
		return "Deleted successfully";
	}

	/**
	 * Deletes all class scheduling entries.
	 */
	@DeleteMapping(path = "/deleteAll")
	public void deleteAll() {
		log.info("Deleting all schedules");
		classSchedulingService.deleteAll();
		log.info("Deleted all schedules successfully");
	}

	/**
	 * Updates a class scheduling entry by ID.
	 *
	 * @param classScheduling The updated class scheduling entry.
	 * @return A ResponseEntity containing the updated class scheduling entry.
	 */

	@PutMapping(path = "/updateById/{id}")
	public ResponseEntity<ClassScheduling> update(@RequestBody ClassScheduling classScheduling) {
		log.info("Updating class scheduling with ID: {}", classScheduling.getClassId());
		ClassScheduling scheduleUpdate = classSchedulingService.update(classScheduling);
		log.info("Updated class scheduling successfully");
		ResponseEntity<ClassScheduling> responseEntity = new ResponseEntity<>(scheduleUpdate, HttpStatus.OK);
		return responseEntity;
	}
}