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

@Repository
public class ViewTrainingDao extends AbstractTransactionalDao{
	
	@Autowired
	ViewTrainingConfig viewTrainingConfig;

	class ViewTrainingInfoRowMapper implements RowMapper<ViewTrainingDetailsDto>{
		
		@Override
		public ViewTrainingDetailsDto mapRow(ResultSet rs, int rowNum) throws SQLException{
			System.out.println("in rowmapper");
			ViewTrainingDetailsDto viewTrainingDetailsDto = new ViewTrainingDetailsDto();
				String courseImg=rs.getString("courseImg");
				String courseName=rs.getString("courseName");
				String instructorName=rs.getString("instructorName");
				String courseStartDate=rs.getString("courseStartDate"); //   DATE -> STRING FORMAT
				String courseEndDate=rs.getString("courseEndDate");
				String shortDescription=rs.getString("shortDescription");
				int coursePrice=rs.getInt("coursePrice");
				return new ViewTrainingDetailsDto(courseImg,courseName,instructorName,courseStartDate,courseEndDate,shortDescription,coursePrice);
		}
	}

//	@Autowired 
//	ViewTrainingInfoRowMapper viewTrainingRowMapper;
//	
//	private static final ViewTrainingInfoRowMapper viewTrainingRowMapper = new ViewTrainingInfoRowMapper();
	public Collection<ViewTrainingDetailsDto> getTrainingDao(){
		System.out.println("in here - dao");
		Map<String,Object> parameters = new HashMap<>();
		System.out.println("DAO QUERY"+ viewTrainingConfig.getTrainingInfo());
		return getJdbcTemplate().query("SELECT courses.course_img AS courseImg, courses.course_name AS courseName, courses.course_start_date AS courseStartDate, courses.course_end_date AS courseEndDate, courses.short_description AS shortDescription, courses.course_price AS coursePrice, instructors.instructor_name AS instructorName FROM courses INNER JOIN instructors on courses.instructor_id = instructors.instructor_id;\r\n" ,parameters,new ViewTrainingInfoRowMapper());
	}
}


