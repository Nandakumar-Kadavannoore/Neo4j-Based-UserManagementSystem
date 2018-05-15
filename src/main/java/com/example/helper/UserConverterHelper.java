package com.example.helper;

import com.example.entity.Application;
import com.example.entity.User;
import com.example.request.UserRequest;

public class UserConverterHelper {
	
	private UserConverterHelper() {
		
	}

	public static User convertUserRequestToResponse(UserRequest userRequest, User user, Application application) {
		user.setEmailId(userRequest.getEmailId());
		user.setFirstName(userRequest.getFirstName());
		user.setLastName(userRequest.getLastName());
		user.setApplication(application);
		return user;
	}

}
