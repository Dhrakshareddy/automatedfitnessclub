package com.healthservice.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.healthservice.entity.Nutrition;
import com.healthservice.bean.NutritionBean;
import com.healthservice.bean.ResponseDto;
import com.healthservice.bean.UserBean;
import com.healthservice.exception.ResourceNotFoundException;
import com.healthservice.repository.UserNutritionRepository;
import com.healthservice.service.UserNutritionService;

@Service
public class UserNutritionServiceImpl implements UserNutritionService {

	private static final Logger log = LoggerFactory.getLogger(UserNutritionServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UserNutritionRepository repository;

	/**
	 * Saves a new nutrition record.
	 *
	 * @param bean The {@link Nutrition} object to save.
	 * @return The saved {@link Nutrition} object.
	 */
	@Override
	public Nutrition saveNewNutrition(Nutrition bean) {
		log.info("Saving new nutrition.");
		return repository.save(bean);
	}

	/**
	 * Retrieves all nutrition records.
	 *
	 * @return A list of {@link Nutrition} objects representing all nutrition
	 *         records.
	 */
	@Override
	public List<Nutrition> getAllNutrition() {
		log.info("Fetching all nutrition records.");
		return repository.findAll();
	}

	/**
	 * Updates a nutrition record.
	 *
	 * @param nutritionId      The ID of the nutrition record to update.
	 * @param updatedNutrition The updated {@link Nutrition} object.
	 * @return The updated {@link Nutrition} object.
	 * @throws IllegalArgumentException if no nutrition record is found with the
	 *                                  given ID.
	 */
	@Override
	public Nutrition updateNutrition(Integer nutritionId, Nutrition updatedNutrition) {
		log.info("Updating nutrition with ID: {}", nutritionId);
		Nutrition existingNutrition = repository.findById(nutritionId)
				.orElseThrow(() -> new IllegalArgumentException("Nutrition with ID " + nutritionId + " not found"));

		existingNutrition.setFoodItem(updatedNutrition.getFoodItem());
		existingNutrition.setCaloriesToBeconsumed(updatedNutrition.getCaloriesToBeconsumed());
		existingNutrition.setProteinToBeconsumed(updatedNutrition.getProteinToBeconsumed());
		existingNutrition.setCarbohydratesToBeconsumed(updatedNutrition.getCarbohydratesToBeconsumed());
		existingNutrition.setUsername(updatedNutrition.getUsername());

		log.info("Nutrition updated successfully.");
		return repository.save(existingNutrition);
	}

	/**
	 * Deletes a nutrition record by ID.
	 *
	 * @param nutritionId The ID of the nutrition record to delete.
	 * @throws ResourceNotFoundException if no nutrition record is found with the
	 *                                   given ID.
	 */
	@Override
	public void deleteById(Integer nutritionId) {
		log.info("Deleting nutrition by ID: {}", nutritionId);
		Optional<Nutrition> optional = repository.findById(nutritionId);
		if (optional.isPresent()) {
			repository.deleteById(nutritionId);
			log.info("Nutrition deleted successfully.");
		} else {
			optional.orElseThrow(
					() -> new ResourceNotFoundException("No nutrition record found with ID: " + nutritionId));
		}
	}

	/**
	 * Maps a {@link Nutrition} entity to a {@link NutritionBean} object.
	 *
	 * @param nutrition The {@link Nutrition} entity to map.
	 * @return The mapped {@link NutritionBean} object.
	 */
	private NutritionBean mapToNutrition(Nutrition nutrition) {
		NutritionBean nutritionBean = new NutritionBean();
		nutritionBean.setFoodItem(nutrition.getFoodItem());
		nutritionBean.setCaloriesToBeconsumed(nutrition.getCaloriesToBeconsumed());
		nutritionBean.setProteinToBeconsumed(nutrition.getProteinToBeconsumed());
		nutritionBean.setCarbohydratesToBeconsumed(nutrition.getCarbohydratesToBeconsumed());
		return nutritionBean;
	}

	@Override
	public ResponseDto getById(Integer nutritionId) {
		log.info("Fetching nutrition by ID: {}", nutritionId);
		ResponseDto responseDto = new ResponseDto();
		Nutrition nutrition = repository.findById(nutritionId)
				.orElseThrow(() -> new ResourceNotFoundException("Nutrition not found with ID: " + nutritionId));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		NutritionBean nutritionBean = mapToNutrition(nutrition);
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<UserBean> responseEntity = restTemplate.exchange(
				"http://13.48.82.196:8402/api/user/getById/" + nutrition.getNutritionId(), HttpMethod.GET, httpEntity,
				UserBean.class);

		UserBean userBean = responseEntity.getBody();

		log.info("User details fetched successfully. Status code: {}", responseEntity.getStatusCode());

		responseDto.setNutritionbean(nutritionBean);
		responseDto.setUserBean(userBean);

		return responseDto;
	}

	@Override
	public List<UserBean> getUserBean() {
		log.info("Fetching all user beans.");
		String url = "http://13.48.82.196:8402/api/user/getAll";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<List<UserBean>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<List<UserBean>>() {
				});

		List<UserBean> userBeans = responseEntity.getBody();
		return userBeans;

	}

	@Override
	public List<Nutrition> getByUsername(String username) {
		log.info("Fetching nutrition records by username: {}", username);
		Optional<List<Nutrition>> optional = repository.findByUsername(username);
		if (optional.isPresent()) {
			log.info("Nutrition records fetched successfully.");
			return optional.get();
		} else {
			optional.orElseThrow(() -> new ResourceNotFoundException("No nutrition found for username: " + username));
		}
		return optional.get();
	}


}
