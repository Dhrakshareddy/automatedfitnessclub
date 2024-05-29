package com.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.paymentservice.entity.Payment;
import com.paymentservice.exceptions.ResourceNotFoundException;
import com.paymentservice.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	/**
	 * Endpoint for saving a payment.
	 *
	 * @param payment The {@link Payment} object to save.
	 * @return A {@link ResponseEntity} containing the saved {@link Payment} object
	 *         with HTTP status code 201 (Created).
	 */
	@PostMapping(path = "/save")
	public ResponseEntity<Payment> save(@RequestBody Payment payment) {
		log.info("Saving payment: {}", payment);

		paymentService.save(payment);

		ResponseEntity<Payment> responseEntity = new ResponseEntity<>(payment, HttpStatus.CREATED);
		log.info("Saved payment successfully");
		return responseEntity;
	}

	/**
	 * Endpoint for fetching a payment by ID.
	 *
	 * @param paymentId The ID of the payment to fetch.
	 * @return A {@link ResponseEntity} containing the fetched {@link Payment}
	 *         object with HTTP status code 200 (OK) if found, or an HTTP status
	 *         code 404 (Not Found) if no payment is found with the given ID.
	 * @throws Exception
	 */
	@GetMapping(path = "/{paymentId}")
	public ResponseEntity<Payment> getPayment(@PathVariable Long paymentId) throws Exception {
		try {
			log.info("Fetching payment by ID: {}", paymentId);
			Payment payment = paymentService.get(paymentId);
			log.info("Fetched payment successfully");
			ResponseEntity<Payment> responseEntity = new ResponseEntity<>(payment, HttpStatus.OK);
			return responseEntity;
		} catch (ResourceNotFoundException e) {
			log.error("Error fetching payment: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	/**
	 * Endpoint for deleting a payment by ID.
	 *
	 * @param paymentId The ID of the payment to delete.
	 * @return A string indicating the status of the operation.
	 */
	@DeleteMapping(path = "/{paymentId}")
	public String deletePayment(@PathVariable Long paymentId) {
		log.info("Deleting payment with ID: {}", paymentId);
		Payment payment = paymentService.deleteById(paymentId);
		log.info("Deleted payment successfully");
		ResponseEntity<Payment> responseEntity = new ResponseEntity<>(payment, HttpStatus.OK);
		return "Record deleted";
	}

	/**
	 * Endpoint for updating a payment's payment mode by ID.
	 *
	 * @param paymentId   The ID of the payment to update.
	 * @param paymentMode The new payment mode.
	 */
	@PutMapping("/update/{paymentId}")
	public void updateEntity(@PathVariable Long paymentId, @RequestParam String paymentMode) {
		log.info("Updating payment with ID: {} and paymentMode: {}", paymentId, paymentMode);
		paymentService.updateEntity(paymentId, paymentMode);
		log.info("Updated payment successfully");
	}
}
