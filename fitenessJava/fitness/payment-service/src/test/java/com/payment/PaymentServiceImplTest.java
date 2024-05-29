package com.payment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
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

import com.paymentservice.bean.PaymentBean;
import com.paymentservice.bean.ResponseDto;
import com.paymentservice.bean.UserBean;
import com.paymentservice.entity.Payment;
import com.paymentservice.exceptions.ResourceNotFoundException;
import com.paymentservice.repository.PaymentRepository;
import com.paymentservice.serviceimpl.PaymentServiceImpl;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

	@InjectMocks
	private PaymentServiceImpl paymentService;

	@Mock
	private PaymentRepository paymentRepository;

	@Mock
	private RestTemplate restTemplate;

	@Test
	void testSave() {
		Payment payment = new Payment();
		payment.setUsername("testUser");

		UserBean userBean = new UserBean();
		userBean.setName("Test User");

		when(restTemplate.exchange(any(String.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(UserBean.class)))
				.thenReturn(ResponseEntity.ok(userBean));

		paymentService.save(payment);

		verify(paymentRepository, times(1)).save(payment);
	}

	@Test
	void testGetPayments() {
		Long paymentId = 1L;

		Payment payment = new Payment();
		payment.setPaymentId(paymentId);

		when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(payment));

		UserBean userBean = new UserBean();
		userBean.setName("Test User");

		when(restTemplate.exchange(any(String.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(UserBean.class)))
				.thenReturn(ResponseEntity.ok(userBean));

		ResponseDto responseDto = paymentService.getPayments(paymentId);

		assertNotNull(responseDto);
		assertEquals(paymentId, responseDto.getPaymentBean().getPaymentId());
		assertEquals("Test User", responseDto.getUserBean().getName());
	}

	@Test
	void testGet() throws Exception {
		Long paymentId = 1L;

		Payment payment = new Payment();
		payment.setPaymentId(paymentId);

		when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(payment));

		assertDoesNotThrow(() -> paymentService.get(paymentId));
		assertEquals(paymentId, paymentService.get(paymentId).getPaymentId());
	}

	@Test
	void testGetNotFound() {
		Long paymentId = 1L;

		when(paymentRepository.findById(paymentId)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> paymentService.get(paymentId));
	}

	@Test
	void testUpdateEntity() {
		Long paymentId = 1L;
		String paymentMode = "Card";

		Payment payment = new Payment();
		payment.setPaymentId(paymentId);

		when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(payment));

		paymentService.updateEntity(paymentId, paymentMode);

		assertEquals(paymentMode, payment.getPaymentMode());
		verify(paymentRepository, times(1)).save(payment);
	}

	@Test
	void testGetPaymentMembership() {
		Payment payment1 = new Payment();
		payment1.setPaymentId(1L);

		Payment payment2 = new Payment();
		payment2.setPaymentId(2L);

		List<Payment> paymentList = Arrays.asList(payment1, payment2);

		when(paymentRepository.getPaymentMembership()).thenReturn(paymentList);

		List<Payment> result = paymentService.getPaymentMembership();

		assertNotNull(result);
		assertEquals(2, result.size());
	}

}