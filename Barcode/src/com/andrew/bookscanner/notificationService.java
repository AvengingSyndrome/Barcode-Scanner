package com.andrew.bookscanner;

import android.content.Context;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.os.Binder;
import android.widget.Toast;
import android.content.Intent;
public class notificationService extends Service {
	
	
	public class LocalBinder extends Binder {
        notificationService getService() {
            return notificationService.this;
        }
	}
	private final Binder mBinder = new LocalBinder();
	
	@Override
    public void onCreate() {
        // Display a notification about us starting.  We put an icon in the status bar.
        showNotification();
    }
	@Override 
	public Binder onBind(Intent i) {
		return mBinder;
	}
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       // Log.i("LocalService", "Received start id " + startId + ": " + intent);
		showNotification();
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        // Tell the user we stopped.
        Toast.makeText(this, "STOPPED", Toast.LENGTH_SHORT).show();
    }
	
    private void showNotification() {
        Notification noti = new Notification.Builder(this)
         .setContentTitle("New mail from ")
         .setContentText("HIHIHIH")
         .setSmallIcon(R.drawable.ic_launcher)
		 .setWhen(System.currentTimeMillis ())
         .getNotification();
		NotificationManager mNotificationManager =
		(NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		mNotificationManager.notify(0, noti);
    }
}