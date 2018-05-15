package com.example.helper;

import com.example.entity.Application;
import com.example.request.ApplicationRequest;

public class ApplicationConverterHelper {
	
	private ApplicationConverterHelper() {
		
	}
	
	public static Application convertApplicationRequestToApplication(ApplicationRequest applicationRequest, Application application) {
		application.setName(applicationRequest.getApplicationName());
		application.setActive(true);
		return application;
	}
	

}
