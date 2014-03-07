package com.example.potato;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	
	private EditText username;
	private EditText password;
	private EditText confirmedPW;
	DatabaseHandler db=new DatabaseHandler(this);
	
	/**
	 * Creates field to input username, password,
	 * and password confirmation.
	 * 
	 * @author Katherine Pham	
	 */
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
	/**
	 * On user click of "Resgister New User" button,
	 * gets string of each user input from username,
	 * password, and confirm password fields, and 
	 * tests them to make sure username and password
	 * are at least 2 characters in length and that
	 * password and confirm password match. If the
	 * fields do not meet this criteria then an error
	 * message is displayed to the user and they are
	 * prompted to try again. If successful username
	 * and corresponding password are added to the
	 * database.
	 * 
	 * @author Katherine Pham
	 * @author Will Cely
	 */
	public void onClickRegisterNewUser(View v){
		String userName=username.getText().toString();
		if (userName.length()<2){
			Toast.makeText(getApplicationContext(), "Username too short.", Toast.LENGTH_SHORT).show();
			return;
		}
		String passWord=password.getText().toString();
		if (passWord.length()<2){
			Toast.makeText(getApplicationContext(), "Password too short", Toast.LENGTH_SHORT).show();
			return;
		}
		String cpw=confirmedPW.getText().toString();
		if (passWord.equals(cpw)&&checkAdd(userName)){
			db.addProfile(new Profile(userName, passWord));
			Current.setProfile(db.getProfile(userName));
			Intent intent = new Intent(this, AccountsActivity.class);
			startActivity(intent);
			finish();
		}
		else if (!checkAdd(userName)){
			Toast.makeText(getApplicationContext(), "Username taken. Please choose again.", Toast.LENGTH_SHORT).show();
		}
		else{
			Toast.makeText(getApplicationContext(), "Passwords do not match. Try again.", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	public boolean checkAdd(String username){
		//Profile profile=db.getProfile(username);
		return true;
	}
	

}
