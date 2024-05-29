package com.traineeservice.exception;

public class NoSuchRecordFoundException extends RuntimeException {

	public NoSuchRecordFoundException() {
		super();
	}

	public NoSuchRecordFoundException(String message) {
		super(message);
	}

	public NoSuchRecordFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
