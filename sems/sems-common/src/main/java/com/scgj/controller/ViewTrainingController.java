package com.scgj.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scgj.dto.ViewTrainingDetailsDto;
import com.scgj.service.ViewTrainingService;

@RestController
public class ViewTrainingController {

	@Autowired
	ViewTrainingService viewTrainingService;
	
	@GetMapping("/vt")
	public Collection<ViewTrainingDetailsDto> viewTrainingDetails(){
		return viewTrainingService.viewTraining();
	}
}
