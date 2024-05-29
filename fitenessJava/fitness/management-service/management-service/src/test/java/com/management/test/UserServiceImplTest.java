package com.management.test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.management.bean.LoginBean;
import com.management.bean.MedicalHistoryBean;
import com.management.entity.User;
import com.management.exception.PasswordMismatchException;
import com.management.exception.ResourceNotFoundException;
import com.management.exception.UserNameNotFoundException;
import com.management.repository.UserRepository;
import com.management.serviceImpl.UserServiceImpl;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        assertEquals(user, userService.saveUser(user));

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetUserById() {
        Long userId = 1L;
        User user = new User();
        user.setUserId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        assertEquals(user, userService.getUserById(userId));

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetUserByIdNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(userId));

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetAll() {
        List<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);

        assertEquals(userList, userService.getAll());

        verify(userRepository, times(1)).findAll();
    }

  

    @Test
    void testValidateLogin() {
        LoginBean loginBean = new LoginBean();
        loginBean.setName("testUser");
        loginBean.setPassword("testPassword");

        User user = new User();
        user.setName("testUser");
        user.setPassword("testPassword");
}

    @Test
    void testUpdate() {
        User user = new User();
        user.setUserId(1L);

        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

        assertEquals(user, userService.update(user));

        verify(userRepository, times(1)).findById(user.getUserId());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testUpdateNotFound() {
        User user = new User();
        user.setUserId(1L);

        when(userRepository.findById(user.getUserId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.update(user));

        verify(userRepository, times(1)).findById(user.getUserId());
        verify(userRepository, never()).save(user);
    }

    @Test
    void testGetUsersByTrainerCode() {
        String trainerCode = "T123";
        List<User> userList = new ArrayList<>();
        when(userRepository.findByTrainerCode(trainerCode)).thenReturn(Optional.of(userList));

        assertEquals(userList, userService.getUsersByTrainerCode(trainerCode));

        verify(userRepository, times(1)).findByTrainerCode(trainerCode);
    }

    @Test
    void testGetUsersByTrainerCodeNotFound() {
        String trainerCode = "NonexistentCode";

        when(userRepository.findByTrainerCode(trainerCode)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.getUsersByTrainerCode(trainerCode));

        verify(userRepository, times(1)).findByTrainerCode(trainerCode);
    }

    @Test
    void testUpdatePassword() {
        String email = "test@example.com";
        String password = "newPassword";

        User user = new User();
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(user);

        assertEquals(user, userService.updatePassword(email, password));

        verify(userRepository, times(1)).findByEmail(email);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testUpdatePasswordUserNotFound() {
        String email = "nonexistent@example.com";
        String password = "newPassword";

        when(userRepository.findByEmail(email)).thenReturn(null);

        assertNull(userService.updatePassword(email, password));

        verify(userRepository, times(1)).findByEmail(email);
        verify(userRepository, never()).save(any());
    }

    @Test
    void testDeleteById() {
        Long userId = 1L;
        User user = new User();
        user.setUserId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        assertEquals(user, userService.deleteById(userId));

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void testDeleteByIdNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.deleteById(userId));

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, never()).deleteById(userId);
    }
}