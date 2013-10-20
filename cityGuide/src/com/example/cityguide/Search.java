package com.example.cityguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Search extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		Button bSearch = (Button)findViewById(R.id.bCreateEvent);
        bSearch.setOnClickListener(
			new View.OnClickListener() {
				public void onClick(View view) {
					Intent goSearch = new Intent (Search.this, CreateEvent.class);
					goSearch.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
					startActivity(goSearch);
					Search.this.finish();
                 }
            });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

}
