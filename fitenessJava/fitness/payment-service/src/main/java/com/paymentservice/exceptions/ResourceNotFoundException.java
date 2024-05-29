package com.paymentservice.exceptions;

public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = -8807717109116125437L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}
}