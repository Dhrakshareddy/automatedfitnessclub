package com.healthservice.exception;

@SuppressWarnings("serial")
public class IllegalArgumentException extends RuntimeException {
	public IllegalArgumentException(String message) {
		super(message);
	}

	public IllegalArgumentException(String message, Exception exception) {
		super(message, exception);
	}
}
