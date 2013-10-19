package com.example.cityguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity {
	Button bLogin;
	Button bSignup;
	EditText etUser;
	EditText etPass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initVars();
	}
	
	public void initVars() {
		etUser = (EditText) findViewById(R.id.etUser);
		etPass = (EditText) findViewById(R.id.etPass);
		
		bLogin = (Button) findViewById(R.id.bLogin);
        bLogin.setOnClickListener(
			new View.OnClickListener() {
				public void onClick(View view) {
					String data = "";
					data += "[L]USER:" + etUser.getText().toString() + "\n";
					data += "[L]PASS:" + etPass.getText().toString() + "\n";
					
					if (MainActivity.mTcpClient != null) {
	                    MainActivity.mTcpClient.sendMessage(data);
	                }
					
					Intent goHomeIntent = new Intent (Login.this, Home.class);
					startActivity(goHomeIntent);
					Login.this.finish();
                 }
            });
        
        bSignup = (Button)findViewById(R.id.bSignup);
        bSignup.setOnClickListener(
			new View.OnClickListener() {
				public void onClick(View view) {
					Intent goSignupIntent = new Intent (Login.this, Signup.class);
					startActivity(goSignupIntent);
					Login.this.finish();
                 }
            });
	}

}
