package com.example.mrs_potato;

import com.example.mrs_potato.DataBaseHandler.InvalidAccountException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterAccount_Activity extends Activity {
	private static Profile currentProfile;
	private EditText accName;
	private EditText bal;
	private EditText inter;
	private EditText accNum;
	private EditText dn;
	DataBaseHandler db=new DataBaseHandler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registeraccount_);
		accName= (EditText)findViewById(R.id.editText1);
		bal = (EditText)findViewById(R.id.editText2);
		inter= (EditText)findViewById(R.id.editText3);
		accNum = (EditText)findViewById(R.id.editText4);
		dn = (EditText)findViewById(R.id.editText5);

		currentProfile = Profile_Activity.currentProfile;
		System.out.println("Account " + currentProfile.getUsername());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registeraccount_, menu);
		return true;
	}

	public void onCancel(View v){
		finish();
	}
	public void onAddAccount(View v) throws InvalidAccountException{
		String accountName = accName.getText().toString();
		String balance = bal.getText().toString();
		double balanceD = Double.valueOf(balance);
		String interest = inter.getText().toString();
		float interestD = Float.valueOf(interest);
		String accountNumber = accNum.getText().toString();
		String displayName = dn.getText().toString();
		//checking to see correct input
		System.out.println("name: " + accountName);
		System.out.println("bal: " + balance);
		System.out.println("interest: " + interest);
		System.out.println("num: " + accountNumber);
		System.out.println("display: " + displayName);
		Account acc = new Account(balanceD, accountName);
		acc.setAccountNumber(accountNumber);
		acc.setDisplayName(displayName);
		acc.setInterest(interestD);
		try {
			db.addAccount(currentProfile, acc);
		}catch (InvalidAccountException e){
			Toast.makeText(getApplicationContext(), "Account already exists..", Toast.LENGTH_SHORT).show();
		}
		finish();

	}
}