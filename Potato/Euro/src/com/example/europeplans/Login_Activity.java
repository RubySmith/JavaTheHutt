package com.example.europeplans;

import com.example.europeplans.DataBaseHandler.InvalidPasswordException;
import com.example.europeplans.DataBaseHandler.InvalidUserException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Login_Activity extends Activity {
	private EditText username;
	private EditText password;
	DataBaseHandler db=new DataBaseHandler(this);
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_);
		username = (EditText)findViewById(R.id.editText1);
		password = (EditText)findViewById(R.id.editText2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_, menu);
		return true;
	}
	
	public void returnToMain(View v){
		finish();
	}
	
	public void onLogin(View v) throws InvalidUserException, InvalidPasswordException{
		System.out.println("lala");
		String userName=username.getText().toString();
		String passWord=password.getText().toString();
		Profile p = db.getProfile(userName, passWord);
//		System.out.println(p.getUsername());
		Intent intent = new Intent(this, Profile_Activity.class);
		startActivity(intent);
		finish();
		
	}

}
