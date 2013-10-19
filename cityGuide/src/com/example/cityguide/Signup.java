package com.example.cityguide;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Signup extends Activity {
	Button bDone;
	EditText etUserSU;
	EditText etPassSU;
	EditText etTownSU;
	EditText etEmailSU;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		initVars();
	}
	
	public void initVars() {
		etUserSU = (EditText) findViewById(R.id.etUserSU);
		etPassSU = (EditText) findViewById(R.id.etPassSU);
		etTownSU = (EditText) findViewById(R.id.etTownSU);
		etEmailSU = (EditText) findViewById(R.id.etEmailSU);
		bDone = (Button)findViewById(R.id.bDone);
        
        bDone.setOnClickListener(
			new View.OnClickListener() {
				public void onClick(View view) {
					String data = "";
					data += "[SU]USER:" + etUserSU.getText().toString() + "\n";
					data += "[SU]PASS:" + etPassSU.getText().toString() + "\n";
					data += "[SU]MAIL:" + etEmailSU.getText().toString() + "\n";
					
					if (MainActivity.mTcpClient != null) {
	                    MainActivity.mTcpClient.sendMessage(data);
	                }
					
					Intent gohome = new Intent (Signup.this, Login.class);
					startActivity(gohome);
					Signup.this.finish();
                 }
            });
	}

}
