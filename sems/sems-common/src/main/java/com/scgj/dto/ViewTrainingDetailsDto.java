package com.scgj.dto;

import com.scgj.common.BaseDto;

public class ViewTrainingDetailsDto extends BaseDto{
	  private String courseImg;
	  private String courseName;
	  private String instructorName;
	  private String courseStartDate;
	  private String courseEndDate;
	  private String shortDescription;
	  private int coursePrice;
	  
	public ViewTrainingDetailsDto() {
		super();
	}
	public ViewTrainingDetailsDto(String courseImg, String courseName, String instructorName,
			String courseStartDate, String courseEndDate, String shortDescription, int coursePrice) {
		// TODO Auto-generated constructor stub
		super(); //			WHY THO?
		this.courseImg = courseImg;
		this.courseName = courseName;
		this.instructorName = instructorName;
		this.courseStartDate = courseStartDate;
		this.courseEndDate = courseEndDate;
		this.shortDescription = shortDescription;
		this.coursePrice = coursePrice;
	}
	public String getCourseImg() {
		return courseImg;
	}
	public void setCourseImg(String courseImg) {
		this.courseImg = courseImg;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getInstructorName() {
		return instructorName;
	}
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}
	public String getCourseStartDate() {
		return courseStartDate;
	}
	public void setCourseStartDate(String courseStartDate) {
		this.courseStartDate = courseStartDate;
	}
	public String getCourseEndDate() {
		return courseEndDate;
	}
	public void setCourseEndDate(String courseEndDate) {
		this.courseEndDate = courseEndDate;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public int getCoursePrice() {
		return coursePrice;
	}
	public void setCoursePrice(int coursePrice) {
		this.coursePrice = coursePrice;
	}
}
