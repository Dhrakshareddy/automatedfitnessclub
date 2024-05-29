package com.management.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.bean.DoctorLoginBean;
import com.management.constants.ManagementConstants;
import com.management.entity.Doctor;
import com.management.exception.PasswordMismatchException;
import com.management.exception.ResourceNotFoundException;
import com.management.repository.DoctorRepository;
import com.management.service.DoctorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	/**
	 * Saves a doctor entity.
	 * 
	 * @param doctor The doctor entity to save.
	 */
	@Override
	public void save(Doctor doctor) {
		doctor.setRole(ManagementConstants.DOCTORROLE);
		doctorRepository.save(doctor);
		log.info("Saved successfully");
	}

	/**
	 * Retrieves a doctor entity by ID.
	 * 
	 * @param id The ID of the doctor entity to retrieve.
	 * @return The retrieved doctor entity.
	 * @throws ResourceNotFoundException If no doctor is found with the given ID.
	 */
	@Override
	public Doctor get(Long id) {
		Optional<Doctor> optional = doctorRepository.findById(id);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(
						() -> new ResourceNotFoundException("No doctor was found to fetch for the given ID:" + id));
			} catch (ResourceNotFoundException e) {
				log.error(e.getMessage());
				throw e;
			}
		}
		doctorRepository.deleteById(id);
		log.info("Fetched successfully");
		return optional.get();
	}

	/**
	 * Retrieves all doctor entities.
	 * 
	 * @return A list of all doctor entities.
	 */

	@Override
	public List<Doctor> getAll() {
		return doctorRepository.findAll();
	}

	/**
	 * Deletes a doctor entity by ID.
	 * 
	 * @param id The ID of the doctor entity to delete.
	 * @return The deleted doctor entity.
	 * @throws ResourceNotFoundException If no doctor is found with the given ID.
	 */

	@Override
	public Doctor deleteById(Long id) {
		Optional<Doctor> optional = doctorRepository.findById(id);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(
						() -> new ResourceNotFoundException("No doctor was found to delete for the given ID:" + id));
			} catch (ResourceNotFoundException e) {
				log.error(e.getMessage());
				throw e;
			}
		}
		doctorRepository.deleteById(id);
		log.info("Deleted successfully");
		return optional.get();
	}

	/**
	 * Updates a doctor entity.
	 * 
	 * @param doctor The doctor entity to update.
	 * @return The updated doctor entity.
	 * @throws ResourceNotFoundException If no doctor is found with the given ID.
	 */

	@Override
	public Doctor update(Doctor doctor) {
		Optional<Doctor> optional = doctorRepository.findById(doctor.getDoctorId());
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new ResourceNotFoundException(
						"No doctor was found to update for the given ID:" + doctor.getDoctorId()));
			} catch (ResourceNotFoundException e) {
				log.error(e.getMessage());
				throw e;
			}
		}
		doctorRepository.save(doctor);
		log.info("Updated successfully");
		return doctor;
	}

	/**
	 * Validates doctor login credentials.
	 * 
	 * @param doctorLoginBean The login credentials of the doctor.
	 * @return The authenticated doctor entity.
	 * @throws PasswordMismatchException If the provided password does not match the
	 *                                   stored password.
	 * @throws UserNameNotFoundException If no doctor is found with the provided
	 *                                   username.
	 */

	@Override
	public Doctor validateLogin(DoctorLoginBean doctorLoginBean) {
		Doctor doctor = doctorRepository.findByName(doctorLoginBean.getName());
		log.info("Doctor found: {}", doctor);

		if (doctor != null) {
			Doctor registrationBean = new Doctor();
			if (doctor.getPassword().equals(doctorLoginBean.getPassword())) {
				log.info("Login successful");
				return doctor;
			} else {
				try {
					throw new PasswordMismatchException("Incorrect password");
				} catch (Exception e) {
					log.error(e.getMessage());
				}
				return doctor;
			}
		} else {
			try {
				throw new UserNameNotFoundException("Incorrect emailId");
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return doctor;
	}

	/**
	 * Retrieves a doctor entity by doctor code.
	 * 
	 * @param doctorCode The unique code of the doctor.
	 * @return The retrieved doctor entity.
	 * @throws ResourceNotFoundException If no doctor is found with the given doctor
	 *                                   code.
	 */

	@Override
	public Doctor getByDoctorCode(String doctorCode) {
		Optional<Doctor> optional = doctorRepository.findByDoctorCode(doctorCode);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new ResourceNotFoundException("No record found:" + doctorCode));
			} catch (ResourceNotFoundException e) {
				log.error(e.getMessage());
				throw e;
			}
		}
		log.info("Fetched successfully");
		return optional.get();
	}
}
