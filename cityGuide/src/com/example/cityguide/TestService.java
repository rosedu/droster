package com.example.cityguide;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TestService extends IntentService {

	private static final String TAG = "com.example.ServiceExample";
	/** 
	   * A constructor is required, and must call the super IntentService(String)
	   * constructor with a name for the worker thread.
	   */
	  public TestService() {
	      super("HelloIntentService");
	  }

	  /**
	   * The IntentService calls this method from the default worker thread with
	   * the intent that started the service. When this method returns, IntentService
	   * stops the service, as appropriate.
	   */
	  @Override
	  protected void onHandleIntent(Intent intent) {
		  Log.i(TAG, "Service onStartCommand");
	      // Normally we would do some work here, like download a file.
	  }
	  
	  @Override
	  public int onStartCommand(Intent intent, int flags, int startId) {
		  Thread thread = new Thread() {
			  
			      @Override
			      public void run() {
			    	  for(int i = 0; i < 10; i ++){
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
		  
		  return super.onStartCommand(intent,flags,startId);
	  }

	

}
