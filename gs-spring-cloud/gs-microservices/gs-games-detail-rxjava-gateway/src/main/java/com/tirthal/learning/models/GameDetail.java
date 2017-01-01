package com.tirthal.learning.models;

import java.util.List;

import com.tirthal.learning.services.recommendations.Game;
import com.tirthal.learning.services.review.Review;

public class GameDetail {
	
	private String glId, title;
	
	private List<Review> reviews;
	
	private List<Game> recommendations;

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

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Game> getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(List<Game> recommendations) {
		this.recommendations = recommendations;
	}
}
