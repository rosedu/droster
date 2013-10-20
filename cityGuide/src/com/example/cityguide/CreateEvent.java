package com.example.cityguide;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class CreateEvent extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_event);
		
		//Buton pentru ???(cautare adica)
				ImageView bSearch = (ImageView)findViewById(R.id.barSearch);
		        bSearch.setOnClickListener(
					new View.OnClickListener() {
						public void onClick(View view) {
							Intent goSearch = new Intent (CreateEvent.this, Search.class);
							goSearch.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
							CreateEvent.this.finish();
							startActivity(goSearch);
		                 }
		            });
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_event, menu);
		return true;
	}

}
