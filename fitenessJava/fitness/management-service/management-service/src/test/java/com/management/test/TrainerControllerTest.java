package com.management.test;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.management.bean.TrainerLoginBean;
import com.management.controller.TrainerController;
import com.management.entity.Trainer;
import com.management.service.TrainerService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TrainerControllerTest {

	@InjectMocks
	private TrainerController trainerController;

	@Mock
	private TrainerService trainerService;

	@Test
	public void testSave() {
		Trainer trainerToSave = new Trainer();

		ResponseEntity<Trainer> response = trainerController.save(trainerToSave);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(trainerToSave, response.getBody());

		verify(trainerService).save(eq(trainerToSave));
	}

	@Test
	public void testGetById() {
		// Given
		Long trainerId = 1L;
		Trainer sampleTrainer = new Trainer();

		when(trainerService.get(eq(trainerId))).thenReturn(sampleTrainer);

		ResponseEntity<Trainer> response = trainerController.get(trainerId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(sampleTrainer, response.getBody());

		verify(trainerService).get(eq(trainerId));
	}

	@Test
	public void testGetAll() {
		List<Trainer> sampleTrainerList = Arrays.asList(/* add sample trainers */);

		when(trainerService.getAll()).thenReturn(sampleTrainerList);

		ResponseEntity<List<Trainer>> response = trainerController.getAll();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(sampleTrainerList, response.getBody());

		verify(trainerService).getAll();
	}

	@Test
	public void testDeleteById() {
		Long trainerId = 1L;

		String response = trainerController.deleteById(trainerId);

		assertEquals("Deleted successfully", response);

		verify(trainerService).deleteById(eq(trainerId));
	}

	@Test
	public void testUpdate() {
		Trainer trainerToUpdate = new Trainer();

		Trainer updatedTrainer = trainerController.update(trainerToUpdate);

		verify(trainerService).update(eq(trainerToUpdate));
	}

	@Test
	public void testLogin() {
		TrainerLoginBean loginBean = new TrainerLoginBean();
		Trainer sampleTrainer = new Trainer();

		when(trainerService.validateLogin(eq(loginBean))).thenReturn(sampleTrainer);

		ResponseEntity<Trainer> response = trainerController.login(loginBean);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(sampleTrainer, response.getBody());

		verify(trainerService).validateLogin(eq(loginBean));
	}

	@Test
	public void testGetByTrainerCode() {
		String trainerCode = "SAMPLE_CODE";
		Trainer sampleTrainer = new Trainer();

		when(trainerService.getByTrainerCode(eq(trainerCode))).thenReturn(sampleTrainer);

		ResponseEntity<Trainer> response = trainerController.getByTrainerCode(trainerCode);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(sampleTrainer, response.getBody());

		verify(trainerService).getByTrainerCode(eq(trainerCode));
	}
}
