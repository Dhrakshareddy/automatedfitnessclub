package com.management.test;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.management.bean.LoginBean;
import com.management.bean.MedicalHistoryBean;
import com.management.controller.UserController;
import com.management.entity.User;
import com.management.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;

	@Test
	public void testSaveUser() {
		User userToSave = new User();

		ResponseEntity<User> response = userController.saveUser(userToSave);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(userToSave, response.getBody());

		verify(userService).saveUser(eq(userToSave));
	}

//	@Test
//	public void testLogin() {
//		LoginBean loginBean = new LoginBean();
//		User sampleUser = new User();
//
//		when(userService.validateLogin(eq(loginBean))).thenReturn(sampleUser);
//
//		ResponseEntity<User> response = userController.login(loginBean);
//
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertEquals(sampleUser, response.getBody());
//
//		verify(userService).validateLogin(eq(loginBean));
//	}

	@Test
	public void testGetUserByName() {
		String name = "SampleName";
		User sampleUser = new User();


	

		verify(userService).getUserByName(eq(name));
	}

	@Test
	public void testGetUserById() {
		Long userId = 1L;
		User sampleUser = new User();

		when(userService.getUserById(eq(userId))).thenReturn(sampleUser);

		ResponseEntity<User> response = userController.getUserById(userId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(sampleUser, response.getBody());

		verify(userService).getUserById(eq(userId));
	}

	@Test
	public void testGetAll() {
		List<User> sampleUserList = Arrays.asList(/* add sample users */);

		when(userService.getAll()).thenReturn(sampleUserList);

		ResponseEntity<List<User>> response = userController.getAll();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(sampleUserList, response.getBody());

		verify(userService).getAll();
	}

	@Test
	public void testDeleteById() {
		Long userId = 1L;

		ResponseEntity<String> response = userController.deleteById(userId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Deleted successfully", response.getBody());

		verify(userService).deleteById(eq(userId));
	}

	@Test
	public void testUpdate() {
		User userToUpdate = new User();

		User updatedUser = userController.update(userToUpdate);

		verify(userService).update(eq(userToUpdate));
	}

	

	@Test
	public void testGetUsersByTrainerCode() {
		String trainerCode = "SampleTrainerCode";
		List<User> userList = Arrays.asList(/* add sample users */);

		when(userService.getUsersByTrainerCode(eq(trainerCode))).thenReturn(userList);

		ResponseEntity<List<User>> response = userController.getUsersByTrainerCode(trainerCode);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(userList, response.getBody());

		verify(userService).getUsersByTrainerCode(eq(trainerCode));
	}

	@Test
	public void testUpdatePassword() {
		String email = "sample@email.com";
		String password = "newPassword";

		User updatedUser = userController.updatePassword(email, password);

		verify(userService).updatePassword(eq(email), eq(password));
	}

	
}
