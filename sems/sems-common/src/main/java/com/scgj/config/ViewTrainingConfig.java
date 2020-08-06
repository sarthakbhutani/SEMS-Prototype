package com.scgj.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//in yml add comment #earliest enrollable, longest course

@Component
@ConfigurationProperties(prefix="viewTrainingQuery")

@PropertySource("classpath:sql/viewTraining.yml")
public class ViewTrainingConfig {
	@Value("${TrainingInfo}") //used with configuration properties
	private String TrainingInfo;
	
	@Value("${TrainingSessionInfoById}")
	private String TrainingSessionInfoById;
	public String getTrainingInfo() {
		return TrainingInfo;
	}
	public void setTrainingInfo(String trainingInfo) {
		TrainingInfo = trainingInfo;
	}
	public String getTrainingSessionInfoById() {
		return TrainingSessionInfoById;
	}
	public void setTrainingSessionInfoById(String trainingSessionInfoById) {
		TrainingSessionInfoById = trainingSessionInfoById;
	}

}
