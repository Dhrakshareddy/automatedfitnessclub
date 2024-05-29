package com.traineeservice;
import com.traineeservice.bean.ResponseDto;
import com.traineeservice.controller.AttendanceController;
import com.traineeservice.entity.Attendance;
import com.traineeservice.service.AttendanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AttendanceControllerTest {

    @Mock
    private AttendanceService attendanceService;

    @InjectMocks
    private AttendanceController attendanceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAttendanceTest() {
        Long attendanceId = 1L;
        ResponseDto responseDto = new ResponseDto(/* Set your test data here */);

        when(attendanceService.getAttendance(attendanceId)).thenReturn(responseDto);

        ResponseEntity<ResponseDto> response = attendanceController.getAttendance(attendanceId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
        verify(attendanceService, times(1)).getAttendance(attendanceId);
    }

    @Test
    void saveAttendanceTest() {
        Attendance attendance = new Attendance(/* Set your test data here */);

        when(attendanceService.saveAttendance(attendance)).thenReturn(attendance);

        ResponseEntity<Attendance> response = attendanceController.save(attendance);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(attendance, response.getBody());
        verify(attendanceService, times(1)).saveAttendance(attendance);
    }

    @Test
    void getAttendanceByNameTest() {
        String name = "testName";
        List<Attendance> attendanceList = Collections.singletonList(new Attendance(/* Set your test data here */));

        when(attendanceService.getAttendanceByName(name)).thenReturn(attendanceList);

        ResponseEntity<List<Attendance>> response = attendanceController.getAttendanceByName(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(attendanceList, response.getBody());
        verify(attendanceService, times(1)).getAttendanceByName(name);
    }

    @Test
    void getAllAttendanceTest() {
        List<Attendance> attendanceList = Collections.singletonList(new Attendance(/* Set your test data here */));

        when(attendanceService.getAll()).thenReturn(attendanceList);

        ResponseEntity<List<Attendance>> response = attendanceController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(attendanceList, response.getBody());
        verify(attendanceService, times(1)).getAll();
    }

    @Test
    void deleteByIdTest() {
        Long attendanceId = 1L;

        when(attendanceService.deleteById(attendanceId)).thenReturn("deleted successfully");

        String response = attendanceController.deleteById(attendanceId);

        assertEquals("deleted successfully", response);
        verify(attendanceService, times(1)).deleteById(attendanceId);
    }
    @Test
    void deleteAllTest() {
        doNothing().when(attendanceService).deleteAll();

        attendanceController.deleteAll();

        verify(attendanceService, times(1)).deleteAll();
    }

    @Test
    void updateAttendanceTest() {
        Attendance attendance = new Attendance(/* Set your test data here */);

        when(attendanceService.update(attendance)).thenReturn(attendance);

        ResponseEntity<Attendance> response = attendanceController.update(attendance);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(attendance, response.getBody());
        verify(attendanceService, times(1)).update(attendance);
    }

}
