package com.healthservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.healthservice.bean.ResponseDto;
import com.healthservice.bean.UserBean;
import com.healthservice.entity.Nutrition;
import com.healthservice.service.UserNutritionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/nutrition")
@CrossOrigin(origins = "http://localhost:4200")
public class NutritionController {

    @Autowired
    UserNutritionService service;

    /**
     * Endpoint for saving a new nutrition entry.
     *
     * @param nutrition The {@link Nutrition} object representing the nutrition entry to save.
     * @return A {@link ResponseEntity} containing the saved {@link Nutrition} object with HTTP status code 201 (Created).
     */
    @PostMapping("/save")
    public ResponseEntity<Nutrition> saveNewNutrition(@RequestBody Nutrition nutrition) {
        log.info("Saving nutrition: {}", nutrition);
        Nutrition saveNutrition = service.saveNewNutrition(nutrition);

        ResponseEntity<Nutrition> responseEntity = new ResponseEntity<>(saveNutrition, HttpStatus.CREATED);
        log.info("Saved nutrition successfully");
        return responseEntity;
    }

    /**
     * Endpoint for fetching a nutrition entry by ID.
     *
     * @param nutritionId The ID of the nutrition entry to fetch.
     * @return A {@link ResponseEntity} containing the {@link ResponseDto} object with HTTP status code 200 (OK).
     */
    @GetMapping("/getById/{nutritionId}")
    public ResponseEntity<ResponseDto> getById(@PathVariable Integer nutritionId) {
        log.info("Fetching nutrition by ID: {}", nutritionId);
        ResponseDto responseDto = service.getById(nutritionId);
        log.info("Fetched nutrition successfully");
        return ResponseEntity.ok(responseDto);
    }

    /**
     * Endpoint for fetching all nutrition entries.
     *
     * @return A list of {@link Nutrition} objects representing all nutrition entries.
     */
    @GetMapping("/getAll")
    public List<Nutrition> getAll() {
        log.info("Fetching all nutrition entries");
        List<Nutrition> nutritionList = service.getAllNutrition();
        log.info("Fetched all nutrition entries successfully");
        return nutritionList;
    }

    /**
     * Endpoint for updating a nutrition entry by ID.
     *
     * @param nutritionId The ID of the nutrition entry to update.
     * @param nutrition   The updated {@link Nutrition} object.
     * @return A {@link ResponseEntity} containing the updated {@link Nutrition} object with HTTP status code 200 (OK) if successful,
     * or an appropriate error message with HTTP status code 404 (Not Found) or 500 (Internal Server Error) if unsuccessful.
     */
    @PutMapping("/updateById/{nutritionId}")
    public ResponseEntity<?> updateNutrition(@PathVariable Integer nutritionId, @RequestBody Nutrition nutrition) {
        try {
            log.info("Updating nutrition with ID: {}", nutritionId);
            Nutrition updatedNutrition = service.updateNutrition(nutritionId, nutrition);
            log.info("Updated nutrition successfully");
            return ResponseEntity.ok(updatedNutrition);
        } catch (IllegalArgumentException e) {
            log.error("Error updating nutrition: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nutrition with ID " + nutritionId + " not found.");
        } catch (Exception e) {
            log.error("Error updating nutrition: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating nutrition.");
        }
    }

    /**
     * Endpoint for deleting a nutrition entry by ID.
     *
     * @param nutritionId The ID of the nutrition entry to delete.
     */
    @DeleteMapping("/deleteById/{nutritionId}")
    public void deleteNutrition(@PathVariable Integer nutritionId) {
        log.info("Deleting nutrition with ID: {}", nutritionId);
        service.deleteById(nutritionId);
        log.info("Deleted nutrition successfully");
    }

    /**
     * Endpoint for fetching all users.
     *
     * @return A list of {@link UserBean} objects representing all users.
     */
    @GetMapping(path = "/getUsers")
    public ResponseEntity<List<UserBean>> getAllUsers() {
        log.info("Fetching all users");
        List<UserBean> userBeans = service.getUserBean();
        log.info("Fetched all users successfully");
        return new ResponseEntity<>(userBeans, HttpStatus.OK);
    }

    /**
     * Endpoint for fetching nutrition entries by username.
     *
     * @param username The username associated with the nutrition entries to fetch.
     * @return A {@link ResponseEntity} containing a list of {@link Nutrition} objects with HTTP status code 200 (OK),
     * or an appropriate error message with HTTP status code 404 (Not Found) if no entries are found.
     */
    @GetMapping("/byName/{username}")
	public ResponseEntity<List<Nutrition>> getByUsername(@PathVariable String username) {
		log.info("Fetching nutrition entries by username: {}", username);
		List<Nutrition> nutritionList = service.getByUsername(username);
		log.info("Fetched nutrition entries by username successfully");
		return new ResponseEntity<>(nutritionList, HttpStatus.OK);
	}
}
