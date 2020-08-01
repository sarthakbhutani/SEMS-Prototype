package com.scgj.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.scgj.common.AbstractTransactionalDao;
import com.scgj.config.ViewTrainingConfig;
import com.scgj.dto.ViewTrainingDetailsDto;
import com.scgj.dto.ViewTrainingSessionDetailsDto;

@Repository
public class ViewTrainingDao extends AbstractTransactionalDao{
	
	@Autowired
	ViewTrainingConfig viewTrainingConfig;

	class ViewTrainingDetailsRowMapper implements RowMapper<ViewTrainingDetailsDto>{
		
		@Override
		public ViewTrainingDetailsDto mapRow(ResultSet rs, int rowNum) throws SQLException{
				int courseId=rs.getInt("courseId");
				String courseImg=rs.getString("courseImg");
				String courseName=rs.getString("courseName");
				String instructorName=rs.getString("instructorName");
				String courseStartDate=rs.getString("courseStartDate"); //   DATE -> STRING FORMAT
				String courseEndDate=rs.getString("courseEndDate");
				String shortDescription=rs.getString("shortDescription");
				int coursePrice=rs.getInt("coursePrice");
				String longDescription=rs.getString("longDescription");
				String assesmentDate=rs.getString("assesmentDate");
				return new ViewTrainingDetailsDto(courseId,courseImg,courseName,instructorName,courseStartDate,courseEndDate,shortDescription,coursePrice,longDescription,assesmentDate);
		}
		
	}
	
	class ViewTrainingSessionDetailsRowMapper implements RowMapper<ViewTrainingSessionDetailsDto>{

		@Override
		public ViewTrainingSessionDetailsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			String sessionName = rs.getString("sessionName");
			String sessionStartTime = rs.getString("sessionStartTime");
			String sessionEndTime = rs.getString("sessionEndTime");
			return new ViewTrainingSessionDetailsDto(sessionName,sessionStartTime, sessionEndTime);
		}
		
	}


	public Collection<ViewTrainingDetailsDto> getTrainingDetailsDao(){
		System.out.println("in here - dao");
		Map<String,Object> parameters = new HashMap<>();
		System.out.println("DAO QUERY"+ viewTrainingConfig.getTrainingInfo());
		return getJdbcTemplate().query("SELECT courses.course_id as courseId, courses.course_img AS courseImg, courses.course_name AS courseName, courses.course_start_date AS courseStartDate, courses.course_end_date AS courseEndDate, courses.short_description AS shortDescription, courses.course_price AS coursePrice, instructors.instructor_name AS instructorName, courses.long_description AS longDescription, courses.assesment_date as assesmentDate  FROM courses INNER JOIN instructors on courses.instructor_id = instructors.instructor_id;\r\n" ,new ViewTrainingDetailsRowMapper());
	}
	
	public Collection<ViewTrainingSessionDetailsDto> getTrainingSessionDetailsByIdDao(int courseId){
		HashMap<String,Integer> parameters = new HashMap<>();
		parameters.put("courseId",courseId);
		System.out.println("DAO QUERY"+ viewTrainingConfig.getTrainingSessionInfoById());
		return getJdbcTemplate().query("SELECT session_name AS sessionName, session_start_time AS sessionStartTime, session_end_time AS sessionEndTime FROM session_details WHERE course_id = :courseId;", parameters, new ViewTrainingSessionDetailsRowMapper());
		
	}
	
}


