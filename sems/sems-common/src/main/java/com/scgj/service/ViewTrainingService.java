package com.scgj.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scgj.dao.ViewTrainingDao;
import com.scgj.dto.ViewTrainingDetailsDto;
import com.scgj.dto.ViewTrainingSessionDetailsDto;

@Service
public class ViewTrainingService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ViewTrainingService.class);

	@Autowired
	ViewTrainingDao viewTrainingDao;

	/**
	 * @param courseId
	 * @return Collection of all courses 
	 * @Description Get All Course Details 
	 */
	public Collection<ViewTrainingDetailsDto> viewTrainingDetailsService() {
		LOGGER.debug("Request received from Controller to ViewTrainingService");
		LOGGER.debug("In method - viewTrainingDetailsService");
		try {
			LOGGER.debug("TRYNG -- To get collection/details of all courses");
			LOGGER.debug("Sending request to viewTrainingDao - getTrainingDetailsDao");
			return viewTrainingDao.getTrainingDetailsDao();
		} catch (Exception e) {
			LOGGER.error("CATCHING -- Exception handled to get collection/details of all courses");
			LOGGER.error("In ViewTrainingService - viewTrainingDetailsService");
			LOGGER.error("Exception is " + e);
			LOGGER.error("Returning NULL");
			return null;
		}

	}

	/**
	 * @param courseId
	 * @return Collection of Session Details for course
	 * @Description Get Session Details for course by courseId
	 */
	public Collection<ViewTrainingSessionDetailsDto> viewTrainingSessionDetailsByIdService(int courseId) {
		LOGGER.debug("Request Recieved from Controller to ViewTrainingService for getting session details for courseId:" + courseId);
		LOGGER.debug("In method viewTrainingSessionDetailsByIdService");
		try {
			LOGGER.debug("TRYING -- viewTrainingSessionDetailsByIdService");
			LOGGER.debug("Sening Request to viewTrainingDao - getTrainingSessionDetailsByIdDao");
			return viewTrainingDao.getTrainingSessionDetailsByIdDao(courseId);
		} catch (Exception e) {
			LOGGER.error("CATCHING -- Exception handled while getting session details for courseId:" + courseId);
			LOGGER.error("In ViewTrainingService - viewTrainingDetailsService");
			LOGGER.error("Exception is " + e);
			LOGGER.error("Returning NULL");
			return null;
		}
	}

}