package com.paymentservice.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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

import com.paymentservice.bean.PaymentBean;
import com.paymentservice.bean.ResponseDto;
import com.paymentservice.bean.UserBean;
import com.paymentservice.entity.Payment;
import com.paymentservice.exceptions.ResourceNotFoundException;
import com.paymentservice.repository.PaymentRepository;
import com.paymentservice.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Maps a {@link Payment} entity to a {@link PaymentBean} object.
	 *
	 * @param payment The {@link Payment} entity to map.
	 * @return The mapped {@link PaymentBean} object.
	 */
	private PaymentBean mapToPayment(Payment payment) {
		PaymentBean paymentBean = new PaymentBean();
		paymentBean.setPaymentId(payment.getPaymentId());
		paymentBean.setPaymentAmount(payment.getPaymentAmount());
		paymentBean.setPaymentDate(payment.getPaymentDate());
		paymentBean.setPaymentMode(payment.getPaymentMode());
		paymentBean.setUsername(payment.getUsername());
		return paymentBean;
	}

	/**
	 * Saves a payment.
	 *
	 * @param payment The {@link Payment} object to save.
	 */
	public void save(Payment payment) {
		String name = payment.getUsername();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		PaymentBean paymentBean = mapToPayment(payment);
		HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
		ResponseEntity<UserBean> responseEntity = restTemplate.exchange(
				"http://localhost:8082/api/user/getByName/" + payment.getUsername(), HttpMethod.GET, httpEntity,
				UserBean.class);
		UserBean userBean = responseEntity.getBody();
		payment.setUsername(userBean.getName());
		paymentRepository.save(payment);
		log.info("Payment saved successfully.");
	}

	/**
	 * Retrieves payments by ID.
	 *
	 * @param paymentId The ID of the payment to retrieve.
	 * @return A {@link ResponseDto} object containing the payment details and
	 *         associated user information.
	 */
	@Override
	public ResponseDto getPayments(Long paymentId) {
		log.info("Fetching payments by ID: {}", paymentId);
		ResponseDto responseDto = new ResponseDto();
		Payment payment = paymentRepository.findById(paymentId).get();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		PaymentBean paymentBean = mapToPayment(payment);
		HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
		ResponseEntity<UserBean> responseEntity = restTemplate.exchange(
				"http://localhost:8082/api/user/getById/" + payment.getUserId(), HttpMethod.GET, httpEntity,
				UserBean.class);
		UserBean userBean = responseEntity.getBody();
		responseDto.setPaymentBean(paymentBean);
		responseDto.setUserBean(userBean);
		log.info("Payments fetched successfully.");
		return responseDto;
	}

	/**
	 * Retrieves a payment by ID.
	 *
	 * @param paymentId The ID of the payment to retrieve.
	 * @return The {@link Payment} object if found.
	 * @throws ResourceNotFoundException if no payment is found with the given ID.
	 */
	@Override
	public Payment get(Long paymentId) throws Exception {
		log.info("Fetching payment by ID: {}", paymentId);
		Optional<Payment> optional = paymentRepository.findById(paymentId);

		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new ResourceNotFoundException("Payment not found with id-" + paymentId));
			} catch (ResourceNotFoundException e) {
				log.error("Exception handled while fetching payment by ID: {}", paymentId, e);
				throw e;
			}
		}

		return optional.get();
	}

	/**
	 * Deletes a payment by ID.
	 *
	 * @param paymentId The ID of the payment to delete.
	 * @return The deleted {@link Payment} object.
	 */
	@Override
	public Payment deleteById(Long paymentId) {
		return null; // Implement deletion logic here
	}

	/**
	 * Updates the payment mode for a payment.
	 *
	 * @param paymentId   The ID of the payment to update.
	 * @param paymentMode The new payment mode.
	 */
	@Transactional
	public void updateEntity(Long paymentId, String paymentMode) {
		Payment payments = paymentRepository.findById(paymentId).orElse(null);
		if (payments != null) {
			payments.setPaymentMode(paymentMode);
			paymentRepository.save(payments);
			log.info("Payment updated successfully. ID: {}, Payment Mode: {}", paymentId, paymentMode);
		}
	}

	/**
	 * Retrieves payment membership records.
	 *
	 * @return A list of payment membership records.
	 */
	@Override
	public List<Payment> getPaymentMembership() {
		log.info("Fetching payment membership records.");
		return paymentRepository.getPaymentMembership();
	}
}
