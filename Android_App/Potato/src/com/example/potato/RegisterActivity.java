package com.example.potato;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	
	private EditText username;
	private EditText password;
	private EditText confirmedPW;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		username = (EditText)findViewById(R.id.editText1);
		password = (EditText)findViewById(R.id.editText2);
		confirmedPW = (EditText)findViewById(R.id.editText3);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}
	
	public void onClickRegisterNewUser(View v){
		if (password.equals(confirmedPW)){
			
		}else{
			Toast.makeText(getApplicationContext(), "Passwords do not match. Try again.", Toast.LENGTH_SHORT).show();
		}
		
	}

}
