package com.traineeservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.traineeservice.bean.ClassSchedulingBean;
import com.traineeservice.bean.ResponseDto;
import com.traineeservice.bean.TrainerBean;
import com.traineeservice.entity.ClassScheduling;
import com.traineeservice.exception.NoSuchRecordFoundException;
import com.traineeservice.repository.ClassSchedulingRepository;
import com.traineeservice.serviceimpl.ClassSchedulingServiceImpl;

@ExtendWith(MockitoExtension.class)
class ClassSchedularServiceImplTest {

    @InjectMocks
    private ClassSchedulingServiceImpl classSchedulingService;

    @Mock
    private ClassSchedulingRepository classSchedulingRepository;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void testGetOneWeekClassScheduling() {
        Date currentDate = new Date();

        when(classSchedulingRepository.findByDateBetween(any(), any())).thenReturn(new ArrayList<>());

        List<ClassScheduling> result = classSchedulingService.getOneWeekClassScheduling();

        assertNotNull(result);
    }


    @Test
    void testSaveClassScheduling() {
        ClassScheduling classScheduling = new ClassScheduling();
        when(classSchedulingRepository.save(classScheduling)).thenReturn(classScheduling);

        ClassScheduling savedClassScheduling = classSchedulingService.saveclClassScheduling(classScheduling);

        assertNotNull(savedClassScheduling);
    }

    @Test
    void testGetAll() {
        List<ClassScheduling> classSchedulingList = new ArrayList<>();
        when(classSchedulingRepository.findAll()).thenReturn(classSchedulingList);

        List<ClassScheduling> result = classSchedulingService.getAll();

        assertNotNull(result);
    }

    @Test
    void testDeleteById() {
        Long classId = 1L;
        Optional<ClassScheduling> optional = Optional.of(new ClassScheduling());
        when(classSchedulingRepository.findById(classId)).thenReturn(optional);

        String result = classSchedulingService.deleteById(classId);

        assertEquals("deleted", result);
    }

    @Test
    void testDeleteByIdNotFound() {
        Long classId = 1L;
        when(classSchedulingRepository.findById(classId)).thenReturn(Optional.empty());

        assertThrows(NoSuchRecordFoundException.class, () -> classSchedulingService.deleteById(classId));
    }


}