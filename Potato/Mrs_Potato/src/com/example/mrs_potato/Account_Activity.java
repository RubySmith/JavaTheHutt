package com.example.mrs_potato;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Property;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Account_Activity extends Activity {
	public static Account currentAccount;
	private String accountName;
	private static double balance;
	private float interest;
	private String accountNumber;
	private String displayName;
	
	RelativeLayout lView ;
	private TextView accName;
	private TextView bal;
//	private TextView inter;
//	private TextView accNum;
//	private TextView dn;


	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_);
		currentAccount = Profile_Activity.getCurrentAccount();
		balance = currentAccount.getBalance();
		currentAccount.setBalance(balance);
//		//Textviews to show up on xml page
		RelativeLayout lView = (RelativeLayout)findViewById(R.id.relativeLayout);
		bal = new TextView(this);
		bal.setX(122);
		bal.setY(330);
		bal.setText("Current Balance: $" + balance);
		lView.addView(bal);

		
	}
	@SuppressLint("NewApi")
	@Override 
	public void onResume(){
		super.onResume();
		RelativeLayout lView = (RelativeLayout)findViewById(R.id.relativeLayout);
		currentAccount = Profile_Activity.getCurrentAccount();
		accountName = currentAccount.getName();
		balance = currentAccount.getBalance();
		interest = currentAccount.getInterest();
		accountNumber = currentAccount.getAccountNumber();
		displayName = currentAccount .getDisplayName();
		accName = new TextView(this);
		accName.setX(140);
		accName.setY(250);
		accName.setText("Account Name: " + accountName);
		bal.setText("Current Balance: $" + balance);
		currentAccount.setBalance(balance);
		System.out.println("ACCount: " + currentAccount.getName() + ", " +currentAccount.getBalance());
		
		lView.addView(accName);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account, menu);
		return true;
	}
	
	public void onAddTransaction(View v){
		Intent intent = new Intent(this, Transaction_Activity.class);
		startActivity(intent);
	}
	public void onCancel(View v){
		finish();
	}

}
