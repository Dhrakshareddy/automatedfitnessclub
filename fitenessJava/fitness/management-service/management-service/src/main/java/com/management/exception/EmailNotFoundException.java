package com.management.exception;

public class EmailNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public EmailNotFoundException(String message) {
		super(message);
	}
	
	public EmailNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public String message() {
		return "Email not found";
	}
} //EXCEPTION
