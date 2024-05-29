package com.paymentservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.paymentservice.entity.Payment;

@Repository
@EnableJpaRepositories
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	@Query(value = "SELECT p.id,p.payment_mode AS payment,p.amount,"
			+ "m.patient_id AS patientId,p.name AS patientName,p.gender,"
			+ "p.contact_no AS contactNo,p.age,p.disease FROM patient p, "
			+ "medicine m WHERE p.id = m.patient_id", nativeQuery = true)
	List<Payment> getPaymentMembership();

}
