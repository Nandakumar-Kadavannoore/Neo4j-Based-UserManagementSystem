package com.example.helper;


import com.example.response.Response;

public class ResponseConverterHelper {
	
	private ResponseConverterHelper() {
		
	}
	
	public static Response getSuccessResponse(String message, Object object) {
		Response response = new Response();
		response.setMessage(message);
		response.setObject(object);
		response.setStatus(true);
		return response;
	}
	
}
