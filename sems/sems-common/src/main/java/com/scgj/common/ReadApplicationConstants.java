package com.scgj.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:applicationConstants.properties")
@ConfigurationProperties(prefix="constant")
public class ReadApplicationConstants
{

	private static String loginOtpMessage;
	private static String smsSenderId;

	
	public static String getSmsSenderId() {
		return smsSenderId;
	}

	public static void setSmsSenderId(String smsSenderId) {
		ReadApplicationConstants.smsSenderId = smsSenderId;
	}

	public static String getLoginOtpMessage() {
		return loginOtpMessage;
	}

	public static void setLoginOtpMessage(String loginOtpMessage) {
		ReadApplicationConstants.loginOtpMessage = loginOtpMessage;
	}
}
