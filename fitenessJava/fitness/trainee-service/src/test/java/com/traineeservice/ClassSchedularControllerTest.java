package com.traineeservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traineeservice.bean.ResponseDto;
import com.traineeservice.controller.ClassSechedularController;
import com.traineeservice.entity.ClassScheduling;
import com.traineeservice.service.ClassSchedulingService;

public class ClassSchedularControllerTest {

	@InjectMocks
	private ClassSechedularController controller;

	@Mock
	private ClassSchedulingService classSchedulingService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetOneWeekClassScheduling() {
		List<ClassScheduling> mockClassSchedulingList = new ArrayList<>();

		when(classSchedulingService.getOneWeekClassScheduling()).thenReturn(mockClassSchedulingList);

		ResponseEntity<List<ClassScheduling>> responseEntity = controller.getOneWeekClassScheduling();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(mockClassSchedulingList, responseEntity.getBody());
	}

	@Test
	public void testGetClassScheduling() {
		Long classId = 1L;
		ResponseDto mockResponseDto = new ResponseDto();

		when(classSchedulingService.getClassscheduling(classId)).thenReturn(mockResponseDto);

		ResponseEntity<ResponseDto> responseEntity = controller.getClassScheduling(classId);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(mockResponseDto, responseEntity.getBody());
	}

	@Test
	public void testSaveAchievement() {
		ClassScheduling mockClassScheduling = new ClassScheduling();

		when(classSchedulingService.saveclClassScheduling(mockClassScheduling)).thenReturn(mockClassScheduling);

		ResponseEntity<ClassScheduling> responseEntity = controller.saveAchievement(mockClassScheduling);

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals(mockClassScheduling, responseEntity.getBody());
	}

	@Test
	void testGetClassesByName() {
		String name = "Test Name";
		List<ClassScheduling> classList = new ArrayList<>();
		classList.add(new ClassScheduling());

		when(classSchedulingService.getClassesByName(name)).thenReturn(classList);

		ResponseEntity<List<ClassScheduling>> responseEntity = controller.getClassesByName(name);

		verify(classSchedulingService, times(1)).getClassesByName(name);

		assert responseEntity.getStatusCode() == HttpStatus.OK;
		assert responseEntity.getBody() != null;
		assert responseEntity.getBody().size() == classList.size(); 
	}

	@Test
	public void testGetAll() {
		List<ClassScheduling> mockClassSchedulingList = new ArrayList<>();

		when(classSchedulingService.getAll()).thenReturn(mockClassSchedulingList);

		ResponseEntity<List<ClassScheduling>> responseEntity = controller.getAll();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(mockClassSchedulingList, responseEntity.getBody());
	}

	@Test
	public void testDeleteById() {
		Long id = 1L;

		String result = controller.deleteById(id);

		assertTrue(result.contains("deleted successfully"));
		verify(classSchedulingService, times(1)).deleteById(id);
	}

	@Test
	public void testDeleteAll() {
		controller.deleteAll();

		verify(classSchedulingService, times(1)).deleteAll();
	}

	@Test
	public void testUpdate() {
		ClassScheduling mockClassScheduling = new ClassScheduling();

		when(classSchedulingService.update(mockClassScheduling)).thenReturn(mockClassScheduling);

		ResponseEntity<ClassScheduling> responseEntity = controller.update(mockClassScheduling);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(mockClassScheduling, responseEntity.getBody());
	}
}