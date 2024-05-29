package com.traineeservice.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.traineeservice.bean.AttendanceBean;
import com.traineeservice.bean.ResponseDto;
import com.traineeservice.bean.UserBean;
import com.traineeservice.entity.Attendance;
import com.traineeservice.exception.NoSuchRecordFoundException;
import com.traineeservice.repository.AttendanceRepository;
import com.traineeservice.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	private static final Logger log = LoggerFactory.getLogger(AttendanceServiceImpl.class);

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Maps an {@link Attendance} entity to its corresponding
	 * {@link AttendanceBean}.
	 *
	 * @param attendance The {@link Attendance} entity to map.
	 * @return The mapped {@link AttendanceBean}.
	 */

	private AttendanceBean mapToAttendance(Attendance attendance) {
		log.info("Entering mapToAttendance method");
		AttendanceBean attendanceBean = new AttendanceBean();
		attendanceBean.setAttendanceId(attendance.getAttendanceId());
		attendanceBean.setDate(attendance.getDate());
		attendanceBean.setStatus(attendance.getStatus());
		attendanceBean.setFeedback(attendance.getFeedback());
		attendanceBean.setUserId(attendance.getUserId());
		log.info("Exiting mapToAttendance method");
		return attendanceBean;
	}

	/**
	 * Retrieves the attendance record by its ID and fetches the corresponding user
	 * details from an external API.
	 *
	 * @param attendanceId The ID of the attendance record to retrieve.
	 * @return A {@link ResponseDto} containing the attendance and user details.
	 * @throws NoSuchRecordFoundException If no attendance record is found with the
	 *                                    given ID.
	 */

	@Override
	public ResponseDto getAttendance(Long attendanceId) {
		log.info("Entering getAttendance method with ID: {}", attendanceId);
		ResponseDto responseDto = new ResponseDto();
		Attendance attendance = attendanceRepository.findById(attendanceId)
				.orElseThrow(() -> new NoSuchRecordFoundException("No attendance found with ID: " + attendanceId));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		AttendanceBean attendanceBean = mapToAttendance(attendance);
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<UserBean> responseEntity = restTemplate.exchange(
				"http://localhost:8082/api/user/getById/" + attendance.getUserId(), HttpMethod.GET, httpEntity,
				UserBean.class);
		UserBean userBean = responseEntity.getBody();
		responseDto.setAttendanceBean(attendanceBean);
		responseDto.setUserBean(userBean);
		log.info("Exiting getAttendance method");
		return responseDto;
	}

	/**
	 * Saves the provided attendance record after retrieving the corresponding user
	 * details from an external API.
	 *
	 * @param attendance The {@link Attendance} entity to save.
	 * @return The saved {@link Attendance} entity.
	 */

	@Override
	public Attendance saveAttendance(Attendance attendance) {
		log.info("Entering saveAttendance method with attendance: {}", attendance);
		String name = attendance.getName();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		AttendanceBean attendanceBean = mapToAttendance(attendance);
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<UserBean> responseEntity = restTemplate.exchange(
				"http://localhost:8082/api/user/getByName/" + attendance.getName(), HttpMethod.GET, httpEntity,
				UserBean.class);
		UserBean userBean = responseEntity.getBody();
		attendance.setUserId(userBean.getUserId());
		Attendance savedAttendance = attendanceRepository.save(attendance);
		log.info("Saved attendance with ID: {}", savedAttendance.getAttendanceId());
		log.info("Exiting saveAttendance method");
		return savedAttendance;
	}

	/**
	 * Retrieves a list of attendance records by the name of the attendee.
	 *
	 * @param name The name of the attendee.
	 * @return A list of {@link Attendance} entities associated with the given name.
	 */

	@Override
	public List<Attendance> getAttendanceByName(String name) {
		log.info("Entering getAttendanceByName method with name: {}", name);
		List<Attendance> result = attendanceRepository.findByName(name);
		log.info("Exiting getAttendanceByName method");
		return result;
	}

	/**
	 * Retrieves all attendance records.
	 *
	 * @return A list of all {@link Attendance} entities.
	 */

	@Override
	public List<Attendance> getAll() {
		log.info("Entering getAll method");
		List<Attendance> result = attendanceRepository.findAll();
		log.info("Exiting getAll method");
		return result;
	}

	/**
	 * Deletes an attendance record by its ID.
	 *
	 * @param attendanceId The ID of the attendance record to delete.
	 * @return A string indicating the deletion operation's success.
	 * @throws NoSuchRecordFoundException If no attendance record is found with the
	 *                                    given ID.
	 */

	@Override
	public String deleteById(Long attendanceId) {
		log.info("Entering deleteById method with ID: {}", attendanceId);
		Optional<Attendance> optional = attendanceRepository.findById(attendanceId);
		if (optional.isEmpty()) {
			throw new NoSuchRecordFoundException("No attendance found with ID: " + attendanceId);
		} else {
			attendanceRepository.deleteById(attendanceId);
		}
		log.info("Exiting deleteById method");
		return "deleted";
	}

	/**
	 * Deletes all attendance records.
	 */
	@Override
	public void deleteAll() {
		log.info("Entering deleteAll method");
		attendanceRepository.deleteAll();
		log.info("Exiting deleteAll method");
	}

	/**
	 * Updates the provided attendance record.
	 *
	 * @param attendance The {@link Attendance} entity to update.
	 * @return The updated {@link Attendance} entity.
	 * @throws NoSuchRecordFoundException If no attendance record is found with the
	 *                                    given ID.
	 */

	@Override
	public Attendance update(Attendance attendance) {
		log.info("Entering update method with attendance: {}", attendance);
		Optional<Attendance> optional = attendanceRepository.findById(attendance.getAttendanceId());
		if (optional.isEmpty()) {
			throw new NoSuchRecordFoundException("No attendance found with ID: " + attendance.getAttendanceId());
		} else {
			attendanceRepository.save(attendance);
		}
		log.info("Exiting update method");
		return attendance;
	}

}