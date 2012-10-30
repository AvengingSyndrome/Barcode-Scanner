package com.andrew.bookscanner;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

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
	

}