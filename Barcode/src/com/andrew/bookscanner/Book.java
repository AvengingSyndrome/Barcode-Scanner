package com.andrew.bookscanner;

public class Book {
	private String jsonString;
	public Book (String googleBookString) {
		jsonString = googleBookString;
	}
	public String getGoogleBookString () {
		return jsonString;
	}
}