package com.andrew.bookscanner;
import android.content.Intent;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.app.Notification;
import android.app.NotificationManager;
public class broadCaster extends BroadcastReceiver
{

@Override
public void onReceive(Context context, Intent intent)
{
	setNotificationAlarm(context, intent);
}

/** Set repeating notifications every 24 hours. */
public static void setNotificationAlarm(Context context, Intent intentSent)
{
    Intent intent = new Intent(context, NotifyService.class);
	intent.putExtra("notificationText",intentSent.getStringExtra("notificationText"));
    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
    alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 10000, pendingIntent);
}


}