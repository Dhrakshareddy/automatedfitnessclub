package com.healthservice.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.healthservice.bean.MedicalHistoryBean;
import com.healthservice.bean.ResponseDto;
import com.healthservice.bean.UserBean;
import com.healthservice.entity.MedicalHistory;
import com.healthservice.exception.ResourceNotFoundException;
import com.healthservice.repository.MedicalHistoryRepository;
import com.healthservice.service.MedicalHistoryService;

@Service
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

	private static final Logger log = LoggerFactory.getLogger(MedicalHistoryServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private MedicalHistoryRepository repository;
//hi

	/**
	 * Retrieves all medical history records.
	 *
	 * @return A list of {@link MedicalHistory} objects representing all medical
	 *         history records.
	 */
	@Override
	public List<MedicalHistory> getAllMedicalHistory() {
		log.info("Fetching all medical history records.");
		return repository.findAll();
	}

	/**
	 * Updates a medical history record.
	 *
	 * @param bean The {@link MedicalHistory} object containing updated information.
	 * @return The updated {@link MedicalHistory} object.
	 */
	@Override
	public MedicalHistory updateMedicalHistory(MedicalHistory bean) {
		log.info("Updating medical history with ID: {}", bean.getMedicalHistoryId());
		Optional<MedicalHistory> optional = repository.findById(bean.getMedicalHistoryId());
		if (optional.isPresent()) {
			repository.save(bean);
			log.info("Medical history updated successfully.");
		}

		return bean;
	}

	/**
	 * Maps a {@link MedicalHistory} entity to a {@link MedicalHistoryBean} object.
	 *
	 * @param medicalHistory The {@link MedicalHistory} entity to map.
	 * @return The mapped {@link MedicalHistoryBean} object.
	 */
	private MedicalHistoryBean mapToMedicalHistory(MedicalHistory medicalHistory) {
		MedicalHistoryBean medicalHistoryBean = new MedicalHistoryBean();
		medicalHistoryBean.setMedicalHistoryId(medicalHistory.getMedicalHistoryId());
		medicalHistoryBean.setDateOfAssessment(medicalHistory.getDateOfAssessment());

		return medicalHistoryBean;
	}

	/**
	 * Saves a new medical history record.
	 *
	 * @param medicalHistory The {@link MedicalHistory} object to save.
	 * @return The saved {@link MedicalHistory} object.
	 */
	@Override
	public MedicalHistory saveNewMedicalHistory(MedicalHistory medicalHistory) {
		log.info("Saving new medical history.");
		return repository.save(medicalHistory);
	}

	/**
	 * Retrieves a medical history record by ID.
	 *
	 * @param medicalHistoryId The ID of the medical history record to retrieve.
	 * @return A {@link ResponseDto} object containing the medical history record
	 *         and associated user details.
	 * @throws ResourceNotFoundException if no medical history record is found with
	 *                                   the given ID.
	 */
	@Override
	public ResponseDto getById(Long medicalHistoryId) {
		log.info("Fetching medical history by ID: {}", medicalHistoryId);
		ResponseDto responseDto = new ResponseDto();

		MedicalHistory medicalHistory = repository.findById(medicalHistoryId).orElseThrow(
				() -> new ResourceNotFoundException("Medical history not found with ID: " + medicalHistoryId));

		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		MedicalHistoryBean medicalHistoryBean = mapToMedicalHistory(medicalHistory);
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<UserBean> responseEntity = restTemplate.exchange(
				"http://localhost:8082/api/user/getByName/" + medicalHistory.getUsername(), HttpMethod.GET, httpEntity,
				UserBean.class);
		UserBean userBean = responseEntity.getBody();
		medicalHistory.setUsername(userBean.getName());

		log.info("User details fetched successfully. Status code: {}", responseEntity.getStatusCode());

		responseDto.setMedicalHistory(medicalHistoryBean);
		responseDto.setUserBean(userBean);

		return responseDto;
	}

	/**
	 * Deletes a medical history record by ID.
	 *
	 * @param medicalHistoryId The ID of the medical history record to delete.
	 * @throws ResourceNotFoundException if no medical history record is found with
	 *                                   the given ID.
	 */
	@Override
	public void deleteById(Long medicalHistoryId) {
		log.info("Deleting medical history by ID: {}", medicalHistoryId);
		Optional<MedicalHistory> optional = repository.findById(medicalHistoryId);
		if (optional.isPresent()) {
			repository.deleteById(medicalHistoryId);
			log.info("Medical history deleted successfully.");
		} else {
			optional.orElseThrow(() -> new ResourceNotFoundException(
					"No medical history record found with ID: " + medicalHistoryId));
		}
	}

	/**
	 * Retrieves medical history records by username.
	 *
	 * @param username The username associated with the medical history records to
	 *                 retrieve.
	 * @return A list of {@link MedicalHistory} objects representing medical history
	 *         records for the given username.
	 */
	@Override
	public List<MedicalHistory> getMedicalHistoryByName(String username) {
		log.info("Fetching medical history records by username: {}", username);
		return repository.findByUsername(username);
	}
}
