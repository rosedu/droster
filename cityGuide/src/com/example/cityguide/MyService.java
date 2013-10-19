package com.example.cityguide;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {

		@Override	  
		public int onStartCommand(Intent intent, int flags, int startId) {
		
			Thread thread = new Thread() {
			  
				@Override
				public void run() {
					for(int i = 0; i < 1; i ++){
						try {
							
							NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
							Notification notification = new Notification(R.drawable.ic_launcher, "notificare", System.currentTimeMillis());
							  
							PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(getApplicationContext(), MainActivity.class), 0);
							notification.setLatestEventInfo(getApplicationContext(), "titlu", "descriere", contentIntent);
							  
							manager.notify(2, notification);
							sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}						    
					}
				}
		    };

		    thread.start();
		    return Service.START_STICKY;
	 	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
