package com.andrew.ISBNLookup;
public class main {
	public static void main(String[] args) {
		System.out.println(ISBNLookup.getISBN10("000"));
		System.out.println(ISBNLookup.getISBN10("9780755322831"));
		System.out.println(ISBNLookup.getBookDetails("000"));
	}
}