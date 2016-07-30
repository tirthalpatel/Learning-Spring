package com.tirthal.learning;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="gs.demo")
public class GsDemoSettings {
	
	private String tryToOverrideMe;
	
	private String secretPassword;

	public String getTryToOverrideMe() {
		return tryToOverrideMe;
	}

	public void setTryToOverrideMe(String tryToOverrideMe) {
		this.tryToOverrideMe = tryToOverrideMe;
	}

	public String getSecretPassword() {
		return secretPassword;
	}

	public void setSecretPassword(String secretPassword) {
		this.secretPassword = secretPassword;
	}
}
