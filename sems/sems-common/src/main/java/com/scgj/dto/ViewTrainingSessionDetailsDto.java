package com.scgj.dto;

import com.scgj.common.BaseDto;

public class ViewTrainingSessionDetailsDto extends BaseDto{
//fetch only session details via courseID
	private String sessionName;
	private String sessionStartTime;
	private String sessionEndTime;
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
	
	public ViewTrainingSessionDetailsDto() {
		super();
	}
	
	public ViewTrainingSessionDetailsDto(String sessionName,String sessionStartTime,String sessionEndTime) {
		super();
		this.sessionName=sessionName;
		this.sessionStartTime=sessionStartTime;
		this.sessionEndTime=sessionEndTime;
	}
}
