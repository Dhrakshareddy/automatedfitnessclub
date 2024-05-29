package com.payment;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.paymentservice.controller.PaymentController;
import com.paymentservice.entity.Payment;
import com.paymentservice.exceptions.ResourceNotFoundException;
import com.paymentservice.service.PaymentService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PaymentControllerTest {

	@InjectMocks
	private PaymentController paymentController;

	@Mock
	private PaymentService paymentService;

	@Test
	public void testSave() {
		Payment paymentToSave = new Payment();

		ResponseEntity<Payment> response = paymentController.save(paymentToSave);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(paymentToSave, response.getBody());

		verify(paymentService).save(eq(paymentToSave));
	}

	@Test
	public void testGetPayment() throws Exception {
		Long paymentId = 1L;
		Payment samplePayment = new Payment();

		when(paymentService.get(eq(paymentId))).thenReturn(samplePayment);

		ResponseEntity<Payment> response = paymentController.getPayment(paymentId);

		assertEquals(samplePayment, response.getBody());

		verify(paymentService).get(eq(paymentId));
	}

	@Test
	public void testGetPaymentNotFound() throws Exception {
		Long paymentId = 1L;

		when(paymentService.get(eq(paymentId))).thenThrow(new ResourceNotFoundException("Payment not found"));

		ResponseEntity<Payment> response = paymentController.getPayment(paymentId);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNull(response.getBody());
	}

	@Test
	public void testDeletePayment() {
		Long paymentId = 1L;

		when(paymentService.deleteById(eq(paymentId))).thenReturn(new Payment());

		String result = paymentController.deletePayment(paymentId);

		assertEquals("Record deleted", result);

		verify(paymentService).deleteById(eq(paymentId));
	}

	@Test
	public void testUpdateEntity() {
		Long paymentId = 1L;
		String paymentMode = "Credit Card";

		paymentController.updateEntity(paymentId, paymentMode);

		verify(paymentService).updateEntity(eq(paymentId), eq(paymentMode));
	}
}
