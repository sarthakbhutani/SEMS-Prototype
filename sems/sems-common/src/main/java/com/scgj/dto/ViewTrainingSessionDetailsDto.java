package com.scgj.dto;

import com.scgj.common.BaseDto;

/**
* @Description POJO class for fetching session details for course
*/
public class ViewTrainingSessionDetailsDto extends BaseDto{
	private String sessionName;
	private String sessionStartTime;
	private String sessionEndTime;
	private String sessionDate;
	public String getSessionName() {
		return sessionName;
	}
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	public String getSessionStartTme() {
		return sessionStartTime;
	}
	public void setSessionStartTime(String sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}
	public String getSessionEndTime() {
		return sessionEndTime;
	}
	public void setSessionEndTime(String sessionEndTime) {
		this.sessionEndTime = sessionEndTime;
	}
	public String getSessionDate() {
		return sessionDate;
	}
	public void setSessionDate(String sessionDate) {
		this.sessionDate = sessionDate;
	}

	public ViewTrainingSessionDetailsDto() {
		super();
	}
	public ViewTrainingSessionDetailsDto(String sessionName,String sessionDate,String sessionStartTime,String sessionEndTime) {
		super();
		this.sessionName=sessionName;
		this.sessionDate=sessionDate;
		this.sessionStartTime=sessionStartTime;
		this.sessionEndTime=sessionEndTime;
	}
}
