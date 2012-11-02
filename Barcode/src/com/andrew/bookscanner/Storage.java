package com.andrew.bookscanner;
import java.io.FileOutputStream;
import android.content.Context;
public class Storage {
	private final static String FILENAME = "booksV1";
	public static String getBooks() {
		return "";
	}
	public static boolean storeBooks(Book[] books, Context c) {
		try {
			FileOutputStream fos = c.openFileOutput(FILENAME,Context.MODE_PRIVATE);
			fos.write(0x5b);
			for(int i = 0; i < books.length; i ++) 
				fos.write((books[i].getGoogleBookString() + ((i+1 != books.length)?",":"]")).getBytes("UTF-8"));		
		} catch (Exception ex) {
			System.err.println(ex);
			return false;
		}
		return true;
	}
	
}