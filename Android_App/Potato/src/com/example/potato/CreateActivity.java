package com.example.potato;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends Activity {
	
	private EditText accountname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create);
		accountname = (EditText)findViewById(R.id.editText1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create, menu);
		return true;
	}
	
	/*
	 * Checks to see if the account name is long enough
	 * Creates a new account but does not store it anywhere
	 * 
	 * @author Ruby Smith
	 */
	
	public void onClickCreateNewAccount(View v) {
		String accountName=accountname.getText().toString();
		if (accountName.length()<2){
			Toast.makeText(getApplicationContext(), "Account name too short.", Toast.LENGTH_SHORT).show();
			return;
		} else if(accountName.length()>20){
			Toast.makeText(getApplicationContext(), "Account name too long.", Toast.LENGTH_SHORT).show();
			return;
		} else {
			Account newAccount = new Account(accountName);
			Intent intent = new Intent(this, SuccessActivity.class);
			startActivity(intent);
		}
	}
	public void onClickCancel(View v) {
		Intent intent = new Intent(this, ProfileActivity.class);
		startActivity(intent);
	}

}
