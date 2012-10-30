package com.andrew.ISBNLookup;
import org.json.JSONObject;
import java.net.*;
import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
public class ISBNLookup {
	
	public static String calcCheckDigit(String ISBN10) {
		int total = 0;
		if (ISBN10.length()!= 9) 
			throw new IllegalArgumentException("Incorrectly Formatted ISBN10");
		for (int i = 0; i < ISBN10.length(); i++)
			total += (10-i)*Integer.parseInt(ISBN10.substring(i,i+1));
		return Integer.toString(11 - (total % 11));
	}
	public static String getISBN10 (String ISBN) {
		String ISBN10 = null;
		switch (ISBN.length()) {
			case 9:
				ISBN10 = ISBN;
				ISBN10 += calcCheckDigit(ISBN10);
				break;
			case 10:
				ISBN10 = ISBN;
				break;
			case 12:
				ISBN10 = ISBN.substring(3,12);
				ISBN10 += calcCheckDigit(ISBN10);
				break;
			case 13:
				ISBN10 = ISBN.substring(3,12);
				ISBN10 += calcCheckDigit(ISBN10);
				break;
			default:
				throw new IllegalArgumentException("Incorrectly Formatted ISBN");
		}
		return ISBN10;
	}
	
	public static String getBookDetails(String ISBN) {
		try {
			String ISBN10 = getISBN10(ISBN);
			String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + ISBN10;
			String lookUpByISBN = readURL (url);
			return lookUpByISBN;
		} catch (Exception ex) {
			return ex.toString();
		}
	}
	
	private static String readURL (String url) {
		try {
			URL reqURL = new URL(url);
			BufferedReader in = new BufferedReader(
			new InputStreamReader(reqURL.openStream()));
			StringBuilder res = new StringBuilder();
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				res.append(inputLine);
			in.close();
			return res.toString();
		} catch (Exception ex) {
			return ex.toString();
		}
	}
}
