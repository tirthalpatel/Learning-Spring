package com.tirthal.learning;

public class Review {

	private Long id;
	
	private String glId;
	
	private String userName;
	
	private String title;
	
	private String description;
	
	private int rating;	
	
	private String date;

	public Review(Long id, String glId, String userName, String title, String description, int rating, String date) {
		super();
		this.id = id;
		this.glId = glId;
		this.userName = userName;
		this.title = title;
		this.description = description;
		this.rating = rating;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGlId() {
		return glId;
	}

	public void setGlId(String glId) {
		this.glId = glId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
