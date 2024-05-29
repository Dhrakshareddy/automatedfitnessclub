package com.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.management.bean.DoctorLoginBean;
import com.management.entity.Doctor;
import com.management.service.DoctorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/doctor_profile")
@CrossOrigin(origins = "*")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@PostMapping(path = "/add")
	public ResponseEntity<Doctor> save(@RequestBody Doctor doctor) {
		log.info("Saving doctor: {}", doctor);
		doctorService.save(doctor);
		ResponseEntity<Doctor> responseEntity = new ResponseEntity<>(doctor, HttpStatus.CREATED);
		log.info("Doctor saved successfully: {}", doctor);
		return responseEntity;
	}

	@PostMapping("/login")
	public ResponseEntity<Doctor> login(@RequestBody DoctorLoginBean doctorLoginBean) {
		Doctor doctor = doctorService.validateLogin(doctorLoginBean);

		if (doctor != null) {
			log.info("Login successful for doctor: {}", doctor);
			return ResponseEntity.ok(doctor);
		} else {
			log.warn("Login failed for doctor");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}

	@GetMapping(path = "/getById/{id}")
	public ResponseEntity<Doctor> get(@PathVariable Long id) {
		log.info("Fetching doctor with ID: {}", id);
		Doctor doctor = doctorService.get(id);
		ResponseEntity<Doctor> responseEntity = new ResponseEntity<>(doctor, HttpStatus.OK);
		log.info("Doctor fetched successfully: {}", doctor);
		return responseEntity;
	}

	@GetMapping(path = "/getAll")
	public ResponseEntity<List<Doctor>> getAll() {
		log.info("Fetching all doctors");
		List<Doctor> doctorList = doctorService.getAll();
		ResponseEntity<List<Doctor>> responseEntity = new ResponseEntity<>(doctorList, HttpStatus.OK);
		log.info("Fetched all doctors successfully");
		return responseEntity;
	}

	@DeleteMapping(path = "/deleteById/{id}")
	public String deleteById(@PathVariable Long id) {
		log.info("Deleting doctor with ID: {}", id);
		doctorService.deleteById(id);
		log.info("Doctor deleted successfully with ID: {}", id);
		return "Deleted successfully";
	}

	@PutMapping(path = "/updateById")
	public Doctor update(@RequestBody Doctor doctor) {
		Doctor update = doctorService.update(doctor);
		log.info("Updated doctor successfully: {}", update);
		return update;
	}

	@GetMapping(path = "/getByCode/{doctorCode}")
	public ResponseEntity<Doctor> getByDoctorCode(@PathVariable String doctorCode) {
		log.info("Fetching doctor by doctor code: {}", doctorCode);
		Doctor doctor = doctorService.getByDoctorCode(doctorCode);
		ResponseEntity<Doctor> responseEntity = new ResponseEntity<>(doctor, HttpStatus.OK);

		if (doctor != null) {
			log.info("Doctor fetched successfully by doctor code: {}", doctor);
		} else {
			log.warn("No doctor found with the given code: {}", doctorCode);
		}

		return responseEntity;
	}
}
