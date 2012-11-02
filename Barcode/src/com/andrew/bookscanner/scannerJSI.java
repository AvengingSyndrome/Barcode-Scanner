package com.andrew.bookscanner;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import android.content.Context;

public class scannerJSI {
    Scanner mainActivity;

    /** Instantiate the interface and set the context */
    scannerJSI (Scanner c) {
        mainActivity = c;
    }
	
	public void scanSomething() {
		mainActivity.scanSomething();
	}
	public void makeNotifications (String bookName) {
		Intent intent=new Intent(mainActivity,broadCaster.class);
		intent.putExtra("notificationText", bookName);
		mainActivity.sendBroadcast(intent);
	}
	
	public void manualISBNSearch(final String isbn) {
	    // insert some parsing here
	    new Thread ( new Runnable () { public void run () { 
       /*     CharSequence text = "Hello toast!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(mainActivity, text, duration);
            toast.show();*/
            mainActivity.lookUp(isbn);	
		}}).start();
    }
	

}