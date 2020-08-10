package com.scgj.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @return Query
 * @Description Fetches Queries for viewTrainingDao from viewTraining.yml
 */

@Component
@ConfigurationProperties(prefix = "viewTrainingQuery")
@PropertySource("classpath:sql/viewTraining.yml")
public class ViewTrainingConfig {
	@Value("${TrainingInfo}")
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