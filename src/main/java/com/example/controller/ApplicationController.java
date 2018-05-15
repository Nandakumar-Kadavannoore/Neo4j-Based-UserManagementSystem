/**
 * Controller for Application
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

import com.example.request.ApplicationRequest;
import com.example.response.Response;
import com.example.service.ApplicationService;

@RestController
@RequestMapping("/application")
@CrossOrigin
public class ApplicationController {
	
	@Autowired 
	private ApplicationService applicationService;
	
	@PostMapping
	public Response addApplication(
			@Validated @RequestBody ApplicationRequest applicationRequest) {
		return applicationService.addApplication(applicationRequest);
	}
	
	@PutMapping
	public Response updateApplication(
			@Validated @RequestBody ApplicationRequest applicationRequest) {
		return applicationService.updateApplication(applicationRequest);
	}
	
	@GetMapping ("/{applicationId}")
	public Response getApplicationById(@PathVariable("applicationId") String applicationId) {
		return applicationService.getApplicationById(applicationId);
	}
	
	@DeleteMapping ("/{applicationId}")
	public Response deleteApplicationById(@PathVariable("applicationId") String applicationId) {
		return applicationService.deleteApplicationById(applicationId);
	}
	
	@GetMapping ("/{applicationId}/users/all")
	public Response getAllApplications(@PathVariable("applicationId") String applicationId) {
		return applicationService.getAllApplications(applicationId);
	}

}
