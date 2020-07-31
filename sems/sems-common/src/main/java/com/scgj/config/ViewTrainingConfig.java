package com.scgj.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//@Component
//@ConfigurationProperties(prefix="viewTrainingQuery",value="classpath:sql/viewTraining.yml") //CHANGE THIS TO locations? 
// https://github.com/spring-projects/spring-boot/issues/6726

@Component
@ConfigurationProperties(prefix="viewTrainingQuery")
//@Configuration("viewTrainingQuery")
@PropertySource("classpath:sql/viewTraining.yml")
public class ViewTrainingConfig {

	private String TrainingInfo;

	public String getTrainingInfo() {
		return TrainingInfo;
	}

	public void setGetTrainingInfo(String TrainingInfo) {
		this.TrainingInfo = TrainingInfo;
	}
}
