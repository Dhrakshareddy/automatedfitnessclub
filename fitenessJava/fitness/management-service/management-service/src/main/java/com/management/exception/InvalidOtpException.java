package com.management.exception;

public class InvalidOtpException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InvalidOtpException(String message) {
		super(message);
	}
	
	public InvalidOtpException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public String message() {
		return "Invalid OTP";
	}

} //EXCEPTION