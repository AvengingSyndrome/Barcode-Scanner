package com.andrew.bookscanner;
import android.app.Service;
import android.content.Intent;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.IBinder;
public class NotifyService extends Service
{       
@Override
public IBinder onBind(Intent intent)
{
    return null;
}

@Override
public int onStartCommand(Intent intent, int flags, int startId)
{
    try
    {
		Notification noti = new Notification.Builder(this)
         .setContentTitle(intent.getStringExtra("notificationText"))
         .setContentText("sup :)")
         .setSmallIcon(R.drawable.ic_launcher)
		 .setWhen(System.currentTimeMillis ())
         .getNotification();
		NotificationManager mNotificationManager =
		(NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		mNotificationManager.notify(0, noti);
    }
    catch (Exception e)
    {
        // If there is Internet problem we do nothing, don't want to disturb the user.
        e.printStackTrace();
    }

    return super.onStartCommand(intent, flags, startId);
}
}