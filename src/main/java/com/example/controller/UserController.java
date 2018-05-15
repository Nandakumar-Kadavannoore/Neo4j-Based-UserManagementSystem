/**
 * Controller for User Controller
 * @author Nandakumar.K
 */
package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.request.UserRequest;
import com.example.response.Response;
import com.example.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public Response addUser(
			@Validated @RequestBody UserRequest userRequest) {
		return userService.addUser(userRequest);
	}
	
	@PutMapping
	public Response updateUser(
			@Validated @RequestBody UserRequest userRequest) {
		return userService.updateUser(userRequest);
	}
	
	@DeleteMapping ("/{userId}")
	public Response deleteUser(@PathVariable("userId") String userId) {
		return userService.deleteUser(userId);
	}
	
	@GetMapping ("/{userId}")
	public Response getUserById (@PathVariable("userId") String userId) {
		return userService.getUserById(userId);
	}

}
