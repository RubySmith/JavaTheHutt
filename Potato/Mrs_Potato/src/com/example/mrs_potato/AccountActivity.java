package com.example.mrs_potato;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AccountActivity extends Activity {
	public static Account currentAccount;
	private String accountName;
	private double balance;
	private float interest;
	private String accountNumber;
	private String displayName;
	
	RelativeLayout lView ;
	private TextView accName;
	private TextView bal;
	private TextView inter;
	private TextView accNum;
	private TextView dn;

	@SuppressLint("NewApi")//api 11 only
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);
		currentAccount = Profile_Activity.getCurrentAccount();
		System.out.println("current: " + currentAccount);
		accountName = currentAccount.getName();
		balance = currentAccount.getBalance();
		interest = currentAccount.getInterest();
		accountNumber = currentAccount.getAccountNumber();
		displayName = currentAccount .getDisplayName();
		System.out.println(accountName + ", " + balance);
		//Textviews to show up on xml page
		RelativeLayout lView = (RelativeLayout)findViewById(R.id.relativeLayout);
//		accName = new TextView(this);
//		accName.setX(20);
//		accName.setY(50);
//		bal = new TextView(this);
//		bal.setX(110);
//		bal.setY(130);
//		inter = new TextView(this);
//		inter.setX(110);
//		inter.setY(210);
//		accNum = new TextView(this);
//		accNum.setX(0);
//		accNum.setY(290);
//		dn = new TextView(this);
//		dn.setX(30);
//		dn.setY(370);
//		accName.setText("Account Name: " + accountName);
//		bal.setText("Current Balance: $" + balance);
//		inter.setText("Interest: " + interest + "%");
//		System.out.println("AAAA: " + accountNumber);
//		accNum.setText("Account Number: " + accountNumber);
//		dn.setText("Display Name: " + displayName);
//		lView.addView(accName);
//		lView.addView(bal);
//		lView.addView(inter);
//		lView.addView(accNum);
//		lView.addView(dn);
	}
	
	@SuppressLint("NewApi")
	public void onResume(){
//		balance = currentAccount.getBalance();
//		bal = new TextView(this);
//		bal.setX(110);
//		bal.setY(130);
//		lView.addView(bal);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account, menu);
		return true;
	}
	
//	public void onAddTransaction(View v){
//		Intent intent = new Intent(this, Transaction_Activity.class);
//		startActivity(intent);
//		
//	}
//	public void onGenerateSpendingReport(View v){
//		Intent intent = new Intent(this, SpendingReport_Activity.class);
//		startActivity(intent);
//	}
}
