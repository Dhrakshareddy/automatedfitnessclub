package com.traineeservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.traineeservice.bean.ResponseDto;
import com.traineeservice.entity.Attendance;
import com.traineeservice.service.AttendanceService;

@RestController
@RequestMapping(path = "attendance")
@CrossOrigin(origins = "http://localhost:4200")
public class AttendanceController {

	private static final Logger log = LoggerFactory.getLogger(AttendanceController.class);

	@Autowired
	private AttendanceService attendanceService;

	/**
	 * Retrieves attendance details by ID.
	 *
	 * @param attendanceId The ID of the attendance entry to retrieve.
	 * @return A ResponseEntity containing attendance details.
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<ResponseDto> getAttendance(@PathVariable("id") Long attendanceId) {
		log.info("Fetching attendance by ID: {}", attendanceId);
		ResponseDto responseDto = attendanceService.getAttendance(attendanceId);
		log.info("Fetched attendance successfully");
		return ResponseEntity.ok(responseDto);
	}

	/**
	 * Retrieves attendance entries by name.
	 *
	 * @param name The name of the attendee.
	 * @return A ResponseEntity containing a list of attendance entries.
	 */

	@GetMapping("/byName/{name}")
	public ResponseEntity<List<Attendance>> getAttendanceByName(@PathVariable String name) {
		log.info("Fetching attendance entries by name: {}", name);
		List<Attendance> attendanceList = attendanceService.getAttendanceByName(name);
		if (attendanceList.isEmpty()) {
			log.info("No attendance entries found for name: {}", name);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		log.info("Fetched attendance entries by name successfully");
		return new ResponseEntity<>(attendanceList, HttpStatus.OK);
	}

	/**
	 * Saves a new attendance entry.
	 *
	 * @param attendance The attendance entry to save.
	 * @return A ResponseEntity containing the saved attendance entry.
	 */
	@PostMapping("/save")
	public ResponseEntity<Attendance> save(@RequestBody Attendance attendance) {
		log.info("Saving attendance: {}", attendance);
		attendanceService.saveAttendance(attendance);
		log.info("Attendance saved successfully");

		ResponseEntity<Attendance> responseEntity = new ResponseEntity<>(attendance, HttpStatus.CREATED);
		return responseEntity;
	}

	/**
	 * Retrieves all attendance entries.
	 *
	 * @return A ResponseEntity containing a list of all attendance entries.
	 */
	@GetMapping(path = "/getAll")
	public ResponseEntity<List<Attendance>> getAll() {
		log.info("Fetching all attendance entries");
		List<Attendance> attendanceList = attendanceService.getAll();
		log.info("Fetched all attendance entries successfully");
		ResponseEntity<List<Attendance>> responseEntity = new ResponseEntity<>(attendanceList, HttpStatus.OK);
		return responseEntity;
	}

	/**
	 * Deletes an attendance entry by ID.
	 *
	 * @param attendanceId The ID of the attendance entry to delete.
	 * @return A message indicating the deletion operation's success.
	 */
	@DeleteMapping(path = "/deleteById/{id}")
	public String deleteById(@PathVariable Long attendanceId) {
		log.info("Deleting attendance with ID: {}", attendanceId);
		attendanceService.deleteById(attendanceId);
		log.info("Deleted attendance successfully");
		return "Deleted successfully";
	}

	/**
	 * Deletes all attendance entries.
	 */

	@DeleteMapping(path = "/deleteAll")
	public void deleteAll() {
		log.info("Deleting all attendance entries");
		attendanceService.deleteAll();
		log.info("Deleted all attendance entries successfully");
	}

	/**
	 * Updates an attendance entry by ID.
	 *
	 * @param attendance The updated attendance entry.
	 * @return A ResponseEntity containing the updated attendance entry.
	 */

	@PutMapping(path = "/updateById/{id}")
	public ResponseEntity<Attendance> update(@RequestBody Attendance attendance) {
		log.info("Updating attendance with ID: {}", attendance.getAttendanceId());
		Attendance attendanceUpdate = attendanceService.update(attendance);
		log.info("Updated attendance successfully");
		ResponseEntity<Attendance> responseEntity = new ResponseEntity<>(attendanceUpdate, HttpStatus.OK);
		return responseEntity;
	}
}