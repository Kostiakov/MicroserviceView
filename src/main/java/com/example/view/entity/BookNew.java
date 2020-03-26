package com.example.view.entity;

public class BookNew extends LiteratureNew {
	

	private String author;
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BookNew() {
		
	}

	@Override
	public String toString() {
		return "Book [author=" + getAuthor() + ", title=" + getTitle() + ", publisher=" + getPublisher() + ", year=" + getYear() + "]";
	}

}
