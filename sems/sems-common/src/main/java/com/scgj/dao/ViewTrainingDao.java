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
public class ViewTrainingDao extends AbstractTransactionalDao{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ViewTrainingDao.class);
	
	@Autowired
	ViewTrainingConfig viewTrainingConfig;

		class ViewTrainingDetailsRowMapper implements RowMapper<ViewTrainingDetailsDto>{
		
		@Override
		public ViewTrainingDetailsDto mapRow(ResultSet rs, int rowNum) throws SQLException{
				int courseId=rs.getInt("courseId");
				String courseImg=rs.getString("courseImg");
				String courseName=rs.getString("courseName");
				String instructorName=rs.getString("instructorName");
				Date courseStartDateObj =rs.getDate("courseStartDate");
				Date courseEndDateObj =rs.getDate("courseEndDate");
				String shortDescription=rs.getString("shortDescription");
				int coursePrice=rs.getInt("coursePrice");
				String longDescription=rs.getString("longDescription");
				Date assesmentDateObj=rs.getDate("assesmentDate");				
				String categoryString = rs.getString("course_category");
				
				//Converting Courses : String -> Array
				String[] category = (String[])categoryString.split(", ");

				//Formatting Date Obj to Req. String
				String courseStartDate = dateToString(courseStartDateObj);
				String courseEndDate = dateToString(courseEndDateObj);
				String assesmentDate = dateToString(assesmentDateObj);
				return new ViewTrainingDetailsDto(courseId,courseImg,courseName,instructorName,courseStartDate,courseEndDate,shortDescription,coursePrice,longDescription,assesmentDate, category);
		}
		
	}
	
		class ViewTrainingSessionDetailsRowMapper implements RowMapper<ViewTrainingSessionDetailsDto>{

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


	public Collection<ViewTrainingDetailsDto> getTrainingDetailsDao(){
		System.out.println("in here - dao");
		Map<String,Object> parameters = new HashMap<>();
		System.out.println("DAO QUERY"+ viewTrainingConfig.getTrainingInfo());
		return getJdbcTemplate().query(viewTrainingConfig.getTrainingInfo() ,new ViewTrainingDetailsRowMapper());
	}
	
	public Collection<ViewTrainingSessionDetailsDto> getTrainingSessionDetailsByIdDao(int courseId){
		HashMap<String,Integer> parameters = new HashMap<>();
		parameters.put("courseId",courseId);
		System.out.println("DAO QUERY"+ viewTrainingConfig.getTrainingSessionInfoById());
		return getJdbcTemplate().query(viewTrainingConfig.getTrainingSessionInfoById(), parameters, new ViewTrainingSessionDetailsRowMapper());
		
	}
	
	public String dateToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
		String strDate = formatter.format(date);
		return strDate;
	}
	
	
	public String TimestampToTime(Timestamp ts) {
		Date date = new Date(ts.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
		String strTime = formatter.format(date);
		return strTime;
	}
}


