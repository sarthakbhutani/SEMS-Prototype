package com.scgj.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scgj.dto.ViewTrainingDetailsDto;
import com.scgj.dto.ViewTrainingSessionDetailsDto;
import com.scgj.service.ViewTrainingService;

@RestController
public class ViewTrainingController {

	@Autowired
	ViewTrainingService viewTrainingService;
	
	@GetMapping("/getAllCourses")
	public Collection<ViewTrainingDetailsDto> viewTrainingDetailsController(){
		return viewTrainingService.viewTrainingDetailsService();
	}
	
	@GetMapping("/getCourseSession")
	public Collection<ViewTrainingSessionDetailsDto> viewTrainingSessionDetailsController(@RequestParam int courseId){
		return viewTrainingService.viewTrainingSessionDetailsByIdService(courseId);
	}
}
