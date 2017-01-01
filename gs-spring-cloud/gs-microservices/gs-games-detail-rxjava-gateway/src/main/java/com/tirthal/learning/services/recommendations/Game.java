package com.tirthal.learning.services.recommendations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Game {

	private String glId, title;

	public Game() {
		
	}
	
	public String getGlId() {
		return glId;
	}

	public void setGlId(String glId) {
		this.glId = glId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
