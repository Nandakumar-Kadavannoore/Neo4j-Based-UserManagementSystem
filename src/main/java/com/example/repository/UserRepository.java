package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.Application;
import com.example.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmailId(String emailId);
	
	List<User> findByApplication(Application application);

	User findByEmailIdAndApplication(String emailId, Application application);

	List<User> findAllByApplication(Application application);

}
