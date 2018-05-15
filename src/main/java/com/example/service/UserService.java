/**
 * Implementation of User Service
 * @author Nandakumar.K
 */
package com.example.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Application;
import com.example.entity.User;
import com.example.exception.AppException;
import com.example.exception.ErrorCode;
import com.example.helper.ResponseConverterHelper;
import com.example.helper.UserConverterHelper;
import com.example.repository.ApplicationRepository;
import com.example.repository.UserRepository;
import com.example.request.UserRequest;
import com.example.response.Response;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ApplicationRepository applicationRepo;
	
	private static final String USER_CREATED_SUCCESSFULLY = "User Created Successfully";
	private static final String USER_UPDATED_SUCCESSFULLY = "User Updated Successfully";
	private static final String USER_DELETED_SUCCESSFULLY = "User Deleted Successfully";
	private static final String USER_RETERIEVED_SUCCESSFULLY = "User Reterieved Successfully";


	public Response addUser(UserRequest userRequest) {
		// Validate Request Application
		Application application = getApplicationById(userRequest.getApplicationId());
		// Check for duplicate in put of same user under application
		checkForDuplicate(userRequest, application);
		// Convert Input request to User entity
		User user = UserConverterHelper.convertUserRequestToResponse(userRequest, new User(), application);
		// Save to database
		user = saveToDatabase(user);
		// Add User to Application
		addToApplication(application, user);
		// Return response
		return ResponseConverterHelper.getSuccessResponse(USER_CREATED_SUCCESSFULLY, user);
	}
	
	public Response updateUser(UserRequest userRequest) {
		// Validate Request Application
		Application application = getApplicationById(userRequest.getApplicationId());
		// Check for duplicate in put of same user under application
		checkForDuplicate(userRequest, application);
		// Get User by User Id
		User user = getUserByUserId(userRequest.getId());
		// Convert Input request to User entity
		user = UserConverterHelper.convertUserRequestToResponse(userRequest, user, application);
		// Save to database
		user = saveToDatabase(user);
		// Add User to Application
		addToApplication(application, user);
		// Return response
		return ResponseConverterHelper.getSuccessResponse(USER_UPDATED_SUCCESSFULLY, user);
	}
	
	public Response getUserById(String userId) {
		// Get User by User Id
		User user = getUserByUserId(userId);
		// Return Response
		return ResponseConverterHelper.getSuccessResponse(USER_RETERIEVED_SUCCESSFULLY, user);
	}
	
	public Response deleteUser(String userId) {
		// Get User by User Id
		User user = getUserByUserId(userId);
		// Remove User from Application
		removeUserFromApplication(user);
		// Remove User
		removeUserFromDatabase(user);
		// Return Response
		return ResponseConverterHelper.getSuccessResponse(USER_DELETED_SUCCESSFULLY, user);
	}


	/**
	 * Helper Method to add User to Application
	 * @param application
	 * @param user
	 */
	private void addToApplication(Application application, User user) {
		// Add User to the Application
		application.getUsers().add(user);
		applicationRepo.save(application);
	}

	/**
	 * Helper Method for saving changes to database
	 * @param user
	 * @return user
	 */
	private User saveToDatabase(User user) {
	  return userRepo.save(user);
	}

	/**
	 * Helper Method for Checking if duplicate entries of same emailId for same application
	 * @param userRequest
	 * @param application
	 */
	private void checkForDuplicate(UserRequest userRequest, Application application) {
		if (StringUtils.isEmpty(userRequest.getEmailId())) {
			throw new AppException(ErrorCode.BAD_REQUEST, "Empty Email Id");
		}
		
		Optional<User> userOptional = Optional.ofNullable(userRepo.findByEmailIdAndApplication(userRequest.getEmailId(), application));
		if (userOptional.isPresent()) {
			throw new AppException(ErrorCode.BAD_REQUEST, "Already Exist");
		}
	}

	/**
	 * Helper Method to get Application By Application Id
	 * @param applicationId
	 * @return Application
	 */
	private Application getApplicationById(String applicationId) {
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
	 * Helper Method to get User By UserId
	 * @param userId
	 * @return User
	 */
	private User getUserByUserId(String userId) {
		if (StringUtils.isEmpty(userId)) {
			throw new AppException(ErrorCode.BAD_REQUEST, "Empty User Id");
		}
		Optional<User> userOptional = userRepo.findById(Long.parseLong(userId));
        if (!userOptional.isPresent()) {
        	throw new AppException(ErrorCode.BAD_REQUEST, "Invalid User Id");
        }
		return userOptional.get();
	}

	/**
	 * Helper Method for remove User from database
	 * @param user
	 */
	private void removeUserFromDatabase(User user) {
		userRepo.delete(user);	
	}

	/**
	 * Helper Method to remove user from Application
	 * @param user
	 */
	private void removeUserFromApplication(User user) {
		// Get Application of User
		Application application = user.getApplication();
		// Remove User from Application
		application.getUsers().remove(user);
		// Save Changes made to application
		applicationRepo.save(application);
	}

	

}
