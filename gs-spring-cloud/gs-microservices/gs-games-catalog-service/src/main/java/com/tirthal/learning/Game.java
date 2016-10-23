package com.tirthal.learning;

public class Game {

	private Long id;

	private String glId, title;

	private int downloadSizeInMB;

	public Game(Long id, String glId, String title, int downloadSizeInMB) {
		super();
		this.id = id;
		this.glId = glId;
		this.title = title;
		this.downloadSizeInMB = downloadSizeInMB;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDownloadSizeInMB() {
		return downloadSizeInMB;
	}

	public void setDownloadSizeInMB(int downloadSizeInMB) {
		this.downloadSizeInMB = downloadSizeInMB;
	}

}
