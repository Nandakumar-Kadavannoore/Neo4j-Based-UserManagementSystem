package com.example.response;

public class ErrorResponse {

	private boolean success;
	private int errorCode;
	private String message;
	
	/**
	 * 
	 * @return success
	 */
	public boolean isSuccess() {
		return success;
	}
	
	/**
	 * 
	 * @param success
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	/**
	 * 
	 * @return errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}
	
	/**
	 * 
	 * @param errorCode
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	/**
	 * 
	 * @return message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
