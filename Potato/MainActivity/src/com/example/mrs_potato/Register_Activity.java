package com.example.mrs_potato;

import com.example.mrs_potato.DataBaseHandler.InvalidPasswordException;
import com.example.mrs_potato.DataBaseHandler.InvalidUserException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register_Activity extends Activity {
	private EditText username;
	private EditText password;
	private static Profile currentProfile;
	DataBaseHandler db=new DataBaseHandler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_);
		username = (EditText)findViewById(R.id.editText1);
		password = (EditText)findViewById(R.id.editText2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register_, menu);
		return true;
	}
	public static Profile getCurrentProfile(){
		return currentProfile;
	}

	public void onCancel(View v){
		finish();
	}
	public void onRegisterUser(View v) throws InvalidUserException, InvalidPasswordException{
		String userName=username.getText().toString();
		if (userName.length()<2){
			Toast.makeText(getApplicationContext(), "Username too short.", Toast.LENGTH_SHORT).show();
		}
		String passWord=password.getText().toString();
		if (passWord.length()<2){
			Toast.makeText(getApplicationContext(), "Password too short", Toast.LENGTH_SHORT).show();
		}
		if (passWord.length()>2 && userName.length()>2){
			try {
			currentProfile= new Profile(userName, passWord);
			System.out.println(currentProfile.getUsername()+", " + currentProfile.getPassword());
			db.addProfile(currentProfile); //add to database
			Intent intent = new Intent(this, Profile_Activity.class);
			startActivity(intent); //problem
			finish();
			}catch(InvalidUserException e){
				Toast.makeText(getApplicationContext(), "Username taken. Please choose again.", Toast.LENGTH_SHORT).show();
			}catch(Exception e){
				Toast.makeText(getApplicationContext(), "Invalid Password. Please choose again.", Toast.LENGTH_SHORT).show();
			}

		}
	}
}
