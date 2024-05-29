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

import com.management.controller.EquipmentController;
import com.management.entity.Equipment;
import com.management.service.EquipmentService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EquipmentControllerTest {

	@InjectMocks
	private EquipmentController equipmentController;

	@Mock
	private EquipmentService equipmentService;

	@Test
	public void testSave() {
		Equipment equipmentToSave = new Equipment();

		ResponseEntity<Equipment> response = equipmentController.save(equipmentToSave);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(equipmentToSave, response.getBody());

		verify(equipmentService).save(eq(equipmentToSave));
	}

	@Test
	public void testUpdate() {
		Equipment equipmentToUpdate = new Equipment();

		Equipment updatedEquipment = equipmentController.update(equipmentToUpdate);

		verify(equipmentService).update(eq(equipmentToUpdate));
	}

	@Test
	public void testGetById() {
		Long equipmentId = 1L;
		Equipment sampleEquipment = new Equipment();

		when(equipmentService.getById(eq(equipmentId))).thenReturn(sampleEquipment);

		ResponseEntity<Equipment> response = equipmentController.getById(equipmentId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(sampleEquipment, response.getBody());

		verify(equipmentService).getById(eq(equipmentId));
	}

	@Test
	public void testGetAll() {
		List<Equipment> sampleEquipmentList = Arrays.asList(/* add sample equipments */);

		when(equipmentService.getAll()).thenReturn(sampleEquipmentList);

		ResponseEntity<List<Equipment>> response = equipmentController.getAll();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(sampleEquipmentList, response.getBody());

		verify(equipmentService).getAll();
	}

	@Test
	public void testDelete() {
		Long equipmentId = 1L;

		when(equipmentService.delete(eq(equipmentId))).thenReturn(new Equipment());

		ResponseEntity<Equipment> response = equipmentController.delete(equipmentId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());

		verify(equipmentService).delete(eq(equipmentId));
	}
}
