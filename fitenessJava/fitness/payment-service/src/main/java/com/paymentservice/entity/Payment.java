package com.paymentservice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment implements Serializable {
	private static final long serialVersionUID = -1516965327693370237L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Long paymentId;

	@Column(name = "payment_amount")
	private Double paymentAmount;

	
	@Column(name = "payment_mode")
	private String paymentMode;

	@Column(name = "payment_date")

	private String paymentDate;

	@Column(name = "user_name")
	private String username;
	@Column(name = "membership_type")
	private String membershipType;

	@Column(name = "user_Id")
	private Long userId;

}
