/**
 * Service Implementation of Application
 * @author Nandakumar.K
 */
package com.example.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Application;
import com.example.entity.User;
import com.example.exception.AppException;
import com.example.exception.ErrorCode;
import com.example.helper.ApplicationConverterHelper;
import com.example.helper.ResponseConverterHelper;
import com.example.repository.ApplicationRepository;
import com.example.repository.UserRepository;
import com.example.request.ApplicationRequest;
import com.example.response.Response;

@Service
public class ApplicationService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ApplicationRepository applicationRepo;
	
	private static final String APPLICATION_CREATED_SUCCESSFULLY = "Application Created Successfuly";
	private static final String APPLICATION_UPDATED_SUCCESSFULLY = "Application Updated Successfully";
	private static final String APPLICATION_DELETED_SUCCESSFULLY = "Application deleted Successfully";
	private static final String APPLICATION_RETERIEVED_SUCCESSFULLY = "Application Successfully Reterieved";
	private static final String APPLICATION_USERS_RETERIEVED_SUCCESSFULLY = "Application Users Reterieved Successfully";

	public Response addApplication(ApplicationRequest applicationRequest) {
		// Convert Request to Application Entity
		Application application = ApplicationConverterHelper.convertApplicationRequestToApplication(applicationRequest, new Application());
		// Check if an Application of same name exist
		checkForDuplicate(application);
		// Save to database
		saveToDatabase(application);
		// Return Response
		return ResponseConverterHelper.getSuccessResponse(APPLICATION_CREATED_SUCCESSFULLY,application);
	}
	
	public Response updateApplication(ApplicationRequest applicationRequest) {
		// Get Application By Application Id
		Application application = getApplicationByApplicationId(applicationRequest.getId());
		// Convert Request to Application Entity
		application = ApplicationConverterHelper.convertApplicationRequestToApplication(applicationRequest, application);
		// Check if an Application of same name exist
		checkForDuplicate(application);
		// Save to database
		saveToDatabase(application);
		// Return Response
		return ResponseConverterHelper.getSuccessResponse(APPLICATION_UPDATED_SUCCESSFULLY, application);
	}
	
	public Response getApplicationById(String applicationId) {
		// Get Application By Application Id
		Application application = getApplicationByApplicationId(applicationId);
		// Return Response
		return ResponseConverterHelper.getSuccessResponse(APPLICATION_RETERIEVED_SUCCESSFULLY, application);
	}

	public Response deleteApplicationById(String applicationId) {
		// Get Application By Application Id
		Application application = getApplicationByApplicationId(applicationId);
		// Delete from database
		deleteFromDatabase(application);
		// Return Response
		return ResponseConverterHelper.getSuccessResponse(APPLICATION_DELETED_SUCCESSFULLY, null);
	}

	public Response getAllApplications(String applicationId) {
		// Get Application By Application Id
		Application application = getApplicationByApplicationId(applicationId);
		// Get All Users under the Application
		List<User> usersList = getAllUsersUnderApplication(application);
		// Return Response
		return ResponseConverterHelper.getSuccessResponse(APPLICATION_USERS_RETERIEVED_SUCCESSFULLY, usersList);
	}
	
	/**
	 * Helper Method to save Application to database
	 * @param application
	 * @return Application
	 */
	private Application saveToDatabase(Application application) {
		return applicationRepo.save(application);	
	}

	/**
	 * Helper Method to check if an Application same name exist
	 * @param application
	 */
	private void checkForDuplicate(Application application) {
		Optional<Application> applicationOptional =  applicationRepo.findByName(application.getName());
		if (applicationOptional.isPresent()) {
			throw new AppException(ErrorCode.BAD_REQUEST, "An Application With Same Name Exist");
		}	
	}
	
	/**
	 * Helper Method to get Application By Application Id
	 * @param applicationId
	 * @return Application
	 */
	private Application getApplicationByApplicationId(String applicationId) {
		if (StringUtils.isEmpty(applicationId)) {
			throw new AppException(ErrorCode.BAD_REQUEST, "Empty Application Id");
		} 
		Optional<Application> applicationOptional = applicationRepo.findById(Long.parseLong(applicationId));
		if (!applicationOptional.isPresent()) {
			throw new AppException(ErrorCode.BAD_REQUEST, "Invalid Application Id");
		}
		return applicationOptional.get();
	}

	
	/**
	 * Helper Method to delete Application from database with check
	 * @param application
	 */
	private void deleteFromDatabase(Application application) {
		Optional<List<User>> usersListOptional = Optional.ofNullable(userRepo.findByApplication(application));
		if (usersListOptional.isPresent()) {
			throw new AppException(ErrorCode.BAD_REQUEST, "Users are Already Mapped to Application");
		}
		
	}

	/**
	 * Helper Method to get All Users under the Application
	 * @param application
	 * @return List<User>
	 */
	private List<User> getAllUsersUnderApplication(Application application) {
      return application.getUsers();
	}

}
