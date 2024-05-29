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

import com.management.bean.DoctorLoginBean;
import com.management.controller.DoctorController;
import com.management.entity.Doctor;
import com.management.service.DoctorService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DoctorControllerTest {

	@InjectMocks
	private DoctorController doctorController;

	@Mock
	private DoctorService doctorService;

	@Test
	public void testSave() {
		Doctor doctorToSave = new Doctor();

		ResponseEntity<Doctor> response = doctorController.save(doctorToSave);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(doctorToSave, response.getBody());

		verify(doctorService).save(eq(doctorToSave));
	}

	@Test
	public void testLogin() {
		DoctorLoginBean doctorLoginBean = new DoctorLoginBean();
		doctorLoginBean.setUsername("sampleUsername");
		doctorLoginBean.setPassword("samplePassword");

		Doctor sampleDoctor = new Doctor(); // Set a sample doctor for successful login

		when(doctorService.validateLogin(eq(doctorLoginBean))).thenReturn(sampleDoctor);

		ResponseEntity<Doctor> response = doctorController.login(doctorLoginBean);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(sampleDoctor, response.getBody());

		verify(doctorService).validateLogin(eq(doctorLoginBean));
	}

	@Test
	public void testLoginFailure() {
		// Given
		DoctorLoginBean doctorLoginBean = new DoctorLoginBean();
		doctorLoginBean.setUsername("invalidUsername");
		doctorLoginBean.setPassword("invalidPassword");

		when(doctorService.validateLogin(eq(doctorLoginBean))).thenReturn(null);

		ResponseEntity<Doctor> response = doctorController.login(doctorLoginBean);

		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
		assertNull(response.getBody());

		verify(doctorService).validateLogin(eq(doctorLoginBean));
	}

	@Test
	public void testGet() {
		Long doctorId = 1L;
		Doctor sampleDoctor = new Doctor();

		when(doctorService.get(eq(doctorId))).thenReturn(sampleDoctor);

		ResponseEntity<Doctor> response = doctorController.get(doctorId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(sampleDoctor, response.getBody());

		verify(doctorService).get(eq(doctorId));
	}

	@Test
	public void testGetAll() {
		List<Doctor> sampleDoctorList = Arrays.asList(/* add sample doctors */);

		when(doctorService.getAll()).thenReturn(sampleDoctorList);

		ResponseEntity<List<Doctor>> response = doctorController.getAll();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(sampleDoctorList, response.getBody());

		verify(doctorService).getAll();
	}

	@Test
	public void testDeleteById() {
		Long doctorId = 1L;

		String result = doctorController.deleteById(doctorId);

		assertEquals("Deleted successfully", result);

		verify(doctorService).deleteById(eq(doctorId));
	}

	@Test
	public void testUpdate() {
		// Given
		Doctor doctorToUpdate = new Doctor();

		// When
		Doctor updatedDoctor = doctorController.update(doctorToUpdate);

		// Verify that the update method in the doctorService is called with the
		// expected argument
		verify(doctorService).update(eq(doctorToUpdate));
	}

	@Test
	public void testGetByDoctorCode() {
		String doctorCode = "sampleDoctorCode";
		Doctor sampleDoctor = new Doctor();

		when(doctorService.getByDoctorCode(eq(doctorCode))).thenReturn(sampleDoctor);

		ResponseEntity<Doctor> response = doctorController.getByDoctorCode(doctorCode);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(sampleDoctor, response.getBody());

		verify(doctorService).getByDoctorCode(eq(doctorCode));
	}
}
