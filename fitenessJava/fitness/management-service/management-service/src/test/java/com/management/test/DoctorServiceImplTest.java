package com.management.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.management.bean.DoctorLoginBean;
import com.management.entity.Doctor;
import com.management.exception.ResourceNotFoundException;
import com.management.repository.DoctorRepository;
import com.management.serviceImpl.DoctorServiceImpl;

class DoctorServiceImplTest {

	@Mock
	private DoctorRepository doctorRepository;

	@InjectMocks
	private DoctorServiceImpl doctorService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSave() {
		Doctor doctor = new Doctor();
		doctorService.save(doctor);
		verify(doctorRepository, times(1)).save(doctor);
	}

	@Test
	void testGet() {
		Long id = 1L;
		Doctor doctor = new Doctor();
		doctor.setDoctorId(id);

		when(doctorRepository.findById(id)).thenReturn(Optional.of(doctor));

		assertEquals(doctor, doctorService.get(id));

		verify(doctorRepository, times(1)).findById(id);
		verify(doctorRepository, times(1)).deleteById(id);
	}

	@Test
	void testGetNotFound() {
		Long id = 1L;

		when(doctorRepository.findById(id)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> doctorService.get(id));

		verify(doctorRepository, times(1)).findById(id);
		verify(doctorRepository, never()).deleteById(id);
	}

	@Test
	void testGetAll() {
		List<Doctor> doctors = new ArrayList<>();
		when(doctorRepository.findAll()).thenReturn(doctors);

		assertEquals(doctors, doctorService.getAll());

		verify(doctorRepository, times(1)).findAll();
	}

	@Test
	void testDeleteById() {
		Long id = 1L;
		Doctor doctor = new Doctor();
		doctor.setDoctorId(id);

		when(doctorRepository.findById(id)).thenReturn(Optional.of(doctor));

		assertEquals(doctor, doctorService.deleteById(id));

		verify(doctorRepository, times(1)).findById(id);
		verify(doctorRepository, times(1)).deleteById(id);
	}

	@Test
	void testDeleteByIdNotFound() {
		Long id = 1L;

		when(doctorRepository.findById(id)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> doctorService.deleteById(id));

		verify(doctorRepository, times(1)).findById(id);
		verify(doctorRepository, never()).deleteById(id);
	}

	@Test
	void testUpdate() {
		Doctor doctor = new Doctor();
		doctor.setDoctorId(1L);

		when(doctorRepository.findById(doctor.getDoctorId())).thenReturn(Optional.of(doctor));

		assertEquals(doctor, doctorService.update(doctor));

		verify(doctorRepository, times(1)).findById(doctor.getDoctorId());
		verify(doctorRepository, times(1)).save(doctor);
	}

	@Test
	void testUpdateNotFound() {
		Doctor doctor = new Doctor();
		doctor.setDoctorId(1L);

		when(doctorRepository.findById(doctor.getDoctorId())).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> doctorService.update(doctor));

		verify(doctorRepository, times(1)).findById(doctor.getDoctorId());
		verify(doctorRepository, never()).save(doctor);
	}

	@Test
	void testValidateLogin() {
		DoctorLoginBean loginBean = new DoctorLoginBean();
		loginBean.setName("username");
		loginBean.setPassword("password");

		Doctor doctor = new Doctor();
		doctor.setName("username");
		doctor.setPassword("password");

		when(doctorRepository.findByName(loginBean.getName())).thenReturn(doctor);

		assertEquals(doctor, doctorService.validateLogin(loginBean));

		verify(doctorRepository, times(1)).findByName(loginBean.getName());
	}

	@Test
	void testGetByDoctorCode() {
		String doctorCode = "D123";
		Doctor doctor = new Doctor();

		when(doctorRepository.findByDoctorCode(doctorCode)).thenReturn(Optional.of(doctor));

		assertEquals(doctor, doctorService.getByDoctorCode(doctorCode));

		verify(doctorRepository, times(1)).findByDoctorCode(doctorCode);
	}

	@Test
	void testGetByDoctorCodeNotFound() {
		String doctorCode = "NonexistentCode";

		when(doctorRepository.findByDoctorCode(doctorCode)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> doctorService.getByDoctorCode(doctorCode));

		verify(doctorRepository, times(1)).findByDoctorCode(doctorCode);
	}
}