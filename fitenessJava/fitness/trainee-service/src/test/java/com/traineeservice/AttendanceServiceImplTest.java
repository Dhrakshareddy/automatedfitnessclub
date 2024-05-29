package com.traineeservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.traineeservice.bean.AttendanceBean;
import com.traineeservice.bean.ResponseDto;
import com.traineeservice.bean.UserBean;
import com.traineeservice.entity.Attendance;
import com.traineeservice.exception.NoSuchRecordFoundException;
import com.traineeservice.repository.AttendanceRepository;
import com.traineeservice.serviceimpl.AttendanceServiceImpl;

@ExtendWith(MockitoExtension.class)
class AttendanceServiceImplTest {

    @InjectMocks
    private AttendanceServiceImpl attendanceService;

    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private RestTemplate restTemplate;


    @Test
    void testGetAttendanceNotFound() {
        Long attendanceId = 1L;

        when(attendanceRepository.findById(attendanceId)).thenReturn(Optional.empty());

        assertThrows(NoSuchRecordFoundException.class, () -> attendanceService.getAttendance(attendanceId));
        verify(restTemplate, never()).exchange(any(String.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(UserBean.class));
    }

    @Test
    void testSaveAttendance() {
        Attendance attendance = new Attendance();
        attendance.setName("TestUser");

        UserBean expectedUserBean = new UserBean();
        expectedUserBean.setUserId(1L);

        ResponseEntity<UserBean> responseEntity = ResponseEntity.ok(expectedUserBean);
        when(restTemplate.exchange(any(String.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(UserBean.class)))
                .thenReturn(responseEntity);

        when(attendanceRepository.save(any(Attendance.class))).thenReturn(attendance);

        Attendance savedAttendance = attendanceService.saveAttendance(attendance);

        assertNotNull(savedAttendance);
        assertEquals(expectedUserBean.getUserId(), savedAttendance.getUserId());
    }

    @Test
    void testGetAttendanceByName() {
        String name = "TestUser";
        Attendance attendance = new Attendance();
        attendance.setName(name);

        when(attendanceRepository.findByName(name)).thenReturn(Arrays.asList(attendance));

        List<Attendance> result = attendanceService.getAttendanceByName(name);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(name, result.get(0).getName());
    }

    // Add more test cases as needed
}