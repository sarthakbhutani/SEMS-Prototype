package com.scgj.dao;

import java.sql.Array;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.scgj.common.AbstractTransactionalDao;
import com.scgj.config.ViewTrainingConfig;
import com.scgj.dto.ViewTrainingDetailsDto;
import com.scgj.dto.ViewTrainingSessionDetailsDto;

@Repository
public class ViewTrainingDao extends AbstractTransactionalDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ViewTrainingDao.class);

	@Autowired
	ViewTrainingConfig viewTrainingConfig;

	/**
	 * @Description Row mapper for getting all course details
	 *
	 */
	class ViewTrainingDetailsRowMapper implements RowMapper<ViewTrainingDetailsDto> {

		@Override
		public ViewTrainingDetailsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			int courseId = rs.getInt("courseId");
			String courseImg = rs.getString("courseImg");
			String courseName = rs.getString("courseName");
			String instructorName = rs.getString("instructorName");
			Date courseStartDateObj = rs.getDate("courseStartDate");
			Date courseEndDateObj = rs.getDate("courseEndDate");
			String shortDescription = rs.getString("shortDescription");
			int coursePrice = rs.getInt("coursePrice");
			String longDescription = rs.getString("longDescription");
			Date assesmentDateObj = rs.getDate("assesmentDate");
			String categoryString = rs.getString("course_category");

			//Converting Courses : String -> Array
			String[] category = (String[]) categoryString.split(", ");
			//Formatting Date Obj to Req. String
			String courseStartDate = dateToString(courseStartDateObj);
			String courseEndDate = dateToString(courseEndDateObj);
			String assesmentDate = dateToString(assesmentDateObj);
			return new ViewTrainingDetailsDto(courseId, courseImg, courseName, instructorName, courseStartDate, courseEndDate, shortDescription, coursePrice, longDescription, assesmentDate, category);
		}

	}

	/**
	 * @Description Row mapper for getting session details for course by CourseId
	 *
	 */
	class ViewTrainingSessionDetailsRowMapper implements RowMapper<ViewTrainingSessionDetailsDto> {

		@Override
		public ViewTrainingSessionDetailsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			String sessionName = rs.getString("sessionName");
			Timestamp sessionStartTimestamp = rs.getTimestamp("sessionStartTime");
			Timestamp sessionEndTimestamp = rs.getTimestamp("sessionEndTime");

			//Processing Time & Date to Req. String
			Date sessionDateObj = new Date(sessionStartTimestamp.getTime());
			String sessionDate = dateToString(sessionDateObj);
			String sessionStartTime = TimestampToTime(sessionStartTimestamp);
			String sessionEndTime = TimestampToTime(sessionEndTimestamp);
			return new ViewTrainingSessionDetailsDto(sessionName, sessionDate, sessionStartTime, sessionEndTime);
		}
	}

	/**
	 * @return Collection of all course details
	 * @Description Fetching all courses details
	 */
	public Collection<ViewTrainingDetailsDto> getTrainingDetailsDao() {
		LOGGER.debug("Request Recieved from Service");
		LOGGER.debug("In getTrainingDetailsDao - to get all course details");
		try {
			LOGGER.debug("TRYING -- To get all course details");
			LOGGER.debug("Executing QUERY to get all course details");
			return getJdbcTemplate().query(viewTrainingConfig.getTrainingInfo(), new ViewTrainingDetailsRowMapper());
		} catch (Exception e) {
			LOGGER.error("CATCHING -- Exception handled while getting all course details");
			LOGGER.error("In ViewTrainingDao - getTrainingDetailsDao");
			LOGGER.error("Exception is :" + e);
			LOGGER.error("Returning NULL");
			return null;
		}
	}

	/**
	 * @params courseId
	 * @return Collection of all session details
	 * @Description Fetching Collection of all session details for courseId
	 */
	public Collection<ViewTrainingSessionDetailsDto> getTrainingSessionDetailsByIdDao(int courseId) {
		LOGGER.debug("Request Recieved from Service");
		LOGGER.debug("In getTrainingSessionDetailsByIdDao - to get all session details for courseId :" + courseId);
		try {
			LOGGER.debug("TRYING -- To get all session details by courseId :" + courseId);
			LOGGER.debug("Creating HashMap<String,Integer> of params");
			HashMap<String, Integer> parameters = new HashMap<>();
			LOGGER.debug("Inserting value in HashMap");
			parameters.put("courseId", courseId);
			LOGGER.debug("Executing QUERY - To get all session details by courseId :" + courseId);
			return getJdbcTemplate().query(viewTrainingConfig.getTrainingSessionInfoById(), parameters, new ViewTrainingSessionDetailsRowMapper());
		} catch (Exception e) {
			LOGGER.error("CATCHING -- Exception handled while session details by courseId:" + courseId);
			LOGGER.error("In ViewTrainingDao - getTrainingSessionDetailsByIdDao");
			LOGGER.error("Exception is :" + e);
			LOGGER.error("Returning NULL");
			return null;
		}
	}

	/**
	 * @params Date
	 * @return String of date
	 * @Description Converts date obj into DD MM YYYY String
	 */
	private String dateToString(Date date) {
		LOGGER.debug("RowMapper VARIABLE DECLARATION");
		LOGGER.debug("RowMapper => dateToString Method VARIABLE DECLARATION");
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
		String strDate = formatter.format(date);
		return strDate;
	}

	/**
	 * @params Timestamp
	 * @return String of time
	 * @Description Converts Timestamp obj into HH:MM String
	 */
	private String TimestampToTime(Timestamp ts) {
		LOGGER.debug("RowMapper VARIABLE DECLARATION");
		LOGGER.debug("RowMapper => TimestampToTime Method VARIABLE DECLARATION");
		Date date = new Date(ts.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
		String strTime = formatter.format(date);
		return strTime;
	}
}