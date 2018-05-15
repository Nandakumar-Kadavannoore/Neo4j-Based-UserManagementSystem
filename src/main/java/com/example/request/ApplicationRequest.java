package com.example.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ApplicationRequest {
	
	@NotNull
	@NotEmpty
	private String applicationName;
	
	private String id;

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
