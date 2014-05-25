package com.tirthal.learning.xmlconfig.spel;

import java.util.List;

public class Catalog {

	private List<Book> allBooks;
	private List<Book> javaBooks;
	private List<Book> springBooks;
	private List<Book> untaggedBooks;

	public List<Book> getAllBooks() {
		return allBooks;
	}

	public void setAllBooks(List<Book> allBooks) {
		this.allBooks = allBooks;
	}

	public List<Book> getSpringBooks() {
		return springBooks;
	}

	public void setSpringBooks(List<Book> springBooks) {
		this.springBooks = springBooks;
	}

	public List<Book> getJavaBooks() {
		return javaBooks;
	}

	public void setJavaBooks(List<Book> javaBooks) {
		this.javaBooks = javaBooks;
	}

	public List<Book> getUntaggedBooks() {
		return untaggedBooks;
	}

	public void setUntaggedBooks(List<Book> untaggedBooks) {
		this.untaggedBooks = untaggedBooks;
	}

	@Override
	public String toString() {
		return "Catalog [allBooks=" + allBooks + ", javaBooks=" + javaBooks + ", springBooks=" + springBooks
				+ ", untaggedBooks=" + untaggedBooks + "]";
	}
}
