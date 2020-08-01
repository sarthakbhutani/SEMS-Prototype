package com.scgj.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scgj.dao.ViewTrainingDao;
import com.scgj.dto.ViewTrainingDetailsDto;

@Service
public class ViewTrainingService {

	@Autowired
	ViewTrainingDao viewTrainingDao;

	public Collection<ViewTrainingDetailsDto> viewTraining(){
		return viewTrainingDao.getTrainingDao();
	}
}
