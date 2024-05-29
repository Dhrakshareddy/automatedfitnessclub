package com.paymentservice.service;

import java.util.List;

import com.paymentservice.bean.ResponseDto;
import com.paymentservice.entity.Payment;

public interface PaymentService {

	void save(Payment payment);

	Payment get(Long paymentId) throws Exception;

	Payment deleteById(Long paymentId);

	ResponseDto getPayments(Long paymentId);

	void updateEntity(Long paymentId, String paymentMode);

	List<Payment> getPaymentMembership();

}
