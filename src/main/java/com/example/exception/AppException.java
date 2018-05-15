package com.example.exception;


public class AppException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private int errorCode;
	private boolean statusSuccess;
	private String message;
	
	public AppException(ErrorCode code, String message) {
		super(message);
		this.errorCode = code.getCode();
		this.message = message;	
	}
	
	public AppException(ErrorCode code, boolean statusSuccess, String message) {
		super(message);
		this.errorCode = code.getCode();
		this.statusSuccess = statusSuccess;
		this.message = message;	
	}
	
	public AppException(ErrorCode code, boolean statusSuccess, AppException ex) {
		super(ex);
		this.errorCode = code.getCode();
		this.statusSuccess = statusSuccess;
		this.message = ex.getMessage();	
	} 
	
	public AppException(ErrorCode code, AppException ex) {
		super(ex);
		this.errorCode = code.getCode();
		this.message = ex.getMessage();	
	}

	public int getErrorCode() {
		return errorCode;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public boolean isStatusSuccess() {
		return statusSuccess;
	}

}
