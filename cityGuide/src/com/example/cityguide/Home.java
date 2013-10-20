package com.example.cityguide;



import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class Home extends Activity {
	
	public ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		//Buton pentru ???(cautare adica)
		ImageView bSearch = (ImageView)findViewById(R.id.barSearch);
        bSearch.setOnClickListener(
			new View.OnClickListener() {
				public void onClick(View view) {
					Intent goSearch = new Intent (Home.this, Search.class);
					goSearch.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
					startActivity(goSearch);
                 }
            });
        
        
        //Cod pentru LISTVIEW
        list = (ListView)findViewById(R.id.listView1);
		
		ArrayList<String> cities = new ArrayList<String>();
		cities.add("bucuresti");
		cities.add("craiova");
		cities.add("slobozia");
		
		ArrayList<String> dates = new ArrayList<String>();
		dates.add("data1");
		dates.add("data2");
		dates.add("data3");
		
		ArrayList<String> guides = new ArrayList<String>();
		guides.add("Alex");
		guides.add("Razvan");
		guides.add("Matei");
		
		MyAdapter adapter=new MyAdapter(cities, dates, guides, this);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {
	           @Override
				public void onItemClick(AdapterView<?> adaptor, View arg1, int position,
						long id) {
	        	    Intent goToEvent = new Intent (Home.this, Event.class);
					goToEvent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
					startActivity(goToEvent);;
				}
			});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	public void pressTab1(final View view) {
		Toast.makeText(Home.this, "Tabu1", Toast.LENGTH_SHORT).show();
	}
	
	public void pressTab2(final View view) {
		Toast.makeText(Home.this, "Tabu2", Toast.LENGTH_SHORT).show();
	}
	
	public void pressTab3(final View view) {
		Toast.makeText(Home.this, "Tabu3", Toast.LENGTH_SHORT).show();
	}
}