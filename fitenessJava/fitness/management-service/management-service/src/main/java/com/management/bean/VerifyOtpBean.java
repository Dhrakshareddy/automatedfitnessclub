package com.management.bean;

public class VerifyOtpBean {
	private String email;
	private String userEnteredOtp;

	public VerifyOtpBean(String email, String userEnteredOtp) {
		super();
		this.email = email;
		this.userEnteredOtp = userEnteredOtp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserEnteredOtp() {
		return userEnteredOtp;
	}

	public void setUserEnteredOtp(String userEnteredOtp) {
		this.userEnteredOtp = userEnteredOtp;
	}

	public VerifyOtpBean() {
		super();
	}

}