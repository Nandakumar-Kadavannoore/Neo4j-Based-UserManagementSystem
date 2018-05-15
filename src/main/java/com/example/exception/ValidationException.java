package com.example.exception;

public class ValidationException extends Exception {

	/**
	 * This class is used for validation errors.
	 */
	private static final long serialVersionUID = 1L;

	public ValidationException() {
		super("Validation failed");
	}
	
	public ValidationException(String message) {
		super(message);
	}

}
