package com.tirthal.learning.xmlconfig.spel;

public class Book {

	private int id;
	private String name;
	private String tag;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", tag=" + tag + "]";
	}
}
