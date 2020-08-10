package com.scgj.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scgj.dto.ViewTrainingDetailsDto;
import com.scgj.dto.ViewTrainingSessionDetailsDto;
import com.scgj.service.ViewTrainingService;

@RestController
public class ViewTrainingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ViewTrainingController.class);

	@Autowired
	ViewTrainingService viewTrainingService;

	/**
	 * @param courseId
	 * @return Collection of all courses 
	 * @Description Get All Course Details 
	 */
	@GetMapping("/getAllCourses")
	public Collection<ViewTrainingDetailsDto> viewTrainingDetailsController() {
		LOGGER.debug("Request received from frontend to viewTrainingDetailsController");
		try {
			LOGGER.debug("In try block");
			LOGGER.debug("Sending request to service to get all course details");
			return viewTrainingService.viewTrainingDetailsService();
		} catch (Exception e) {
			LOGGER.debug("An error occurred while fetching courses Details at Controller" + e);
			LOGGER.debug("Returning NULL!");
			return null;
		}
	}

	/**
	 * @param courseId
	 * @return Collection of Session Details
	 * @Description Get Session Details for course by courseId
	 */
	@GetMapping("/getCourseSession")
	public Collection<ViewTrainingSessionDetailsDto> viewTrainingSessionDetailsController(@RequestParam int courseId) {
		LOGGER.debug("Request received from frontend to get session details for course by courseId :" + courseId);
		try {
			LOGGER.debug("In try block");
			LOGGER.debug("Sending request to service to get all course details: " + courseId);
			return viewTrainingService.viewTrainingSessionDetailsByIdService(courseId);
		} catch (Exception e) {
			LOGGER.debug("An error occurred while fetching session details at Controller" + e);
			LOGGER.debug("Returning NULL!");
			return null;
		}

	}

}