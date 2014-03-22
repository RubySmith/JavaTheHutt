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
	DatabaseHandler db=new DatabaseHandler(this);

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
		username =(EditText)findViewById(R.id.dayEntry);
		password = (EditText)findViewById(R.id.categoryEntry);
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
		if (check(userName,passWord)){
			Current.setProfile(db.getProfile(userName));
			Intent intent = new Intent(this, AccountsActivity.class);
			startActivity(intent);
			finish();
		}else{
			Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();
			
		}
	}
	
	public boolean check(String userName, String passToChk){
		Profile profile=db.getProfile(userName);
		if (profile==null){
			Toast.makeText(getApplicationContext(), "Username Invalid", Toast.LENGTH_SHORT).show();
			return false;
		}
		else if (!profile.getPassword().equals(passToChk)) return false;
		else return true;
	}
	

}
