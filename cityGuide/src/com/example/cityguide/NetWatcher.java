package com.example.cityguide;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


public class NetWatcher extends BroadcastReceiver {
	
	
	private static final String TAG = "aplicatie";

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//here, check that the network connection is available. If yes, start your service. If not, stop your service.
	       ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	       NetworkInfo info = cm.getActiveNetworkInfo();
	       if (info != null) {
	           if (info.isConnected()) {
	               //start service
	        	   Log.i(TAG, "chestia cu netu");
	               Intent serviceIntent = new Intent(context.getApplicationContext(), MyService.class);
	               context.startService(serviceIntent);
	           }
	           else {
	               //stop service
	               Intent serviceIntent = new Intent(context.getApplicationContext(), MyService.class);
	               context.stopService(serviceIntent);
	           }
	       }
		
	}
		

}
