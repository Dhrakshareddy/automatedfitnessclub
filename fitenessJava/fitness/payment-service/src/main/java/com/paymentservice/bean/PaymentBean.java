package com.paymentservice.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class PaymentBean {

	private Long paymentId;

	private Double paymentAmount;
	private String membershipType;

	private String paymentMode;

	private String paymentDate;

	private String username;

	private Long userId;

}
