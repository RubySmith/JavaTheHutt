package com.example.potato;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private EditText username;
	private EditText password;

	/**
	 * Creates field to input username
	 * and password.
	 * 
	 * @author Katherine Pham	
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		username =(EditText)findViewById(R.id.editText1);
		password = (EditText)findViewById(R.id.editText2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	/**
	 * Checks strings of the username
	 * and password provided by the 
	 * user and checks to see if they
	 * are valid. If they are then
	 * the user is taken to the success
	 * screen and if not then the user
	 * is prompted to try again. 
	 * 
	 * @author Will Cely
	 * @author Katherine Pham	
	 */
	public void onLogin(View v){
		String userName= username.getText().toString();
		String passWord= password.getText().toString();
		if (Database.check(userName,passWord)){
			Intent intent = new Intent(this, ProfileActivity.class);
			startActivity(intent);
		}else{
			Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();
			
		}
	}

}
