package com.tirthal.learning.helloworld.greeting;

/**
 * Sample message POJO
 * 
 * @author tirthalp
 * 
 */
public class Message {

	private String title;
	private String description;

	public Message(String title, String description) {
		this.setTitle(title);
		this.setDescription(description);
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

}
