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
	  private int courseId;
	  private String longDescription;
	  private String assesmentDate;
	  private String[] category;
	public ViewTrainingDetailsDto() {
		super();
	}
	public ViewTrainingDetailsDto(int courseId, String courseImg, String courseName, String instructorName,
			String courseStartDate, String courseEndDate, String shortDescription, int coursePrice, String longDescription, String assesmentDate, String[] category) {
		super();
		this.courseId=courseId;
		this.courseImg = courseImg;
		this.courseName = courseName;
		this.instructorName = instructorName;
		this.courseStartDate = courseStartDate;
		this.courseEndDate = courseEndDate;
		this.shortDescription = shortDescription;
		this.coursePrice = coursePrice;
		this.longDescription=longDescription;
		this.assesmentDate=assesmentDate;
		this.category = category;
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
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getLongDescription() {
		return longDescription;
	}
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	public String getAssesmentDate() {
		return assesmentDate;
	}
	public void setAssesmentDate(String assesmentDate) {
		this.assesmentDate = assesmentDate;
	}
	public String[] getCategory() {
		return category;
	}
	public void setCategory(String[] category) {
		this.category = category;
	}
}
