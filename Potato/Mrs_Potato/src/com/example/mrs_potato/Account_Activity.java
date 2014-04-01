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
	private double balance;
	private float interest;
	private String accountNumber;
	private String displayName;
	
	RelativeLayout lView ;
	private TextView accName;
	private TextView bal ;
	private TextView inter;
	private TextView accNum;
	private TextView dn;


	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_);
//		currentAccount = Profile_Activity.getCurrentAccount();
//		System.out.println("current: " + currentAccount);
//		accountName = currentAccount.getName();
//		balance = currentAccount.getBalance();
//		interest = currentAccount.getInterest();
//		accountNumber = currentAccount.getAccountNumber();
//		displayName = currentAccount .getDisplayName();
//		System.out.println(accountName + ", " + balance);
//		//Textviews to show up on xml page
//		RelativeLayout lView = (RelativeLayout)findViewById(R.id.relativeLayout);
//		accName = new TextView(this);
//		accName.setX(130);
//		accName.setY(50);
//		bal = new TextView(this);
//		bal.setX(110);
//		bal.setY(130);
////		inter = new TextView(this);
////		inter.setX(110);
////		inter.setY(210);
////		accNum = new TextView(this);
////		accNum.setX(0);
////		accNum.setY(290);
////		dn = new TextView(this);
////		dn.setX(30);
////		dn.setY(370);
//		accName.setText("Account Name: " + accountName);
//		bal.setText("Current Balance: $" + balance);
////		inter.setText("Interest: " + interest + "%");
////		accNum.setText("Account Number: " + accountNumber);
////		dn.setText("Display Name: " + displayName);
//		lView.addView(accName);
//		lView.addView(bal);
////		lView.addView(inter);
////		lView.addView(accNum);
////		lView.addView(dn);
//		bal=null;
//		bal = new TextView(this);
//		bal.setX(110);
//		bal.setY(130);
	}
	@SuppressLint("NewApi")
	@Override 
	public void onResume(){
		super.onResume();
		RelativeLayout lView = (RelativeLayout)findViewById(R.id.relativeLayout);
		currentAccount = Profile_Activity.getCurrentAccount();
		System.out.println("current: " + currentAccount);
		accountName = currentAccount.getName();
		balance = currentAccount.getBalance();
		interest = currentAccount.getInterest();
		accountNumber = currentAccount.getAccountNumber();
		displayName = currentAccount .getDisplayName();
		System.out.println(accountName + ", " + balance);
		accName = new TextView(this);
		accName.setX(130);
		accName.setY(50);
		bal = new TextView(this);
		bal.setX(110);
		bal.setY(130);
//		bal.addTextChangedListener(new TextWatcher() {
//		    @Override
//		    public void afterTextChanged(Editable s) {
//		        // TODO Auto-generated method stub
//		    }
//		    @Override
//		    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//		        // TODO Auto-generated method stub
//		    }
//		    @Override
//		    public void onTextChanged(CharSequence s, int start, int before, int count) {
//		        // TODO Auto-generated method stub
////		        if(Property.GetType() == Property.PROPTYPE.num) {
////		            float f = Float.parseFloat(s.toString());
////		            Prop.FromString(f);
////		        }
////		        else {
////		            Prop.FromString(s.toString());
////		        }
////		        propertiesToUpdate.add(Prop);
//		});
		
		accName.setText("Account Name: " + accountName);
		bal.setText("Current Balance: $" + balance);
		
		lView.addView(accName);
		lView.addView(bal);
	}

//	public void onResume(){
//		super.onResume();
//		RelativeLayout lView =(RelativeLayout)findViewById(R.id.relativeLayout);
//		balance = currentAccount.getBalance();
//		bal = new TextView(this);
//		bal.setX(110);
//		bal.setY(130);
//		lView.addView(bal);
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account, menu);
		return true;
	}
	
	public void onAddTransaction(View v){
		Intent intent = new Intent(this, Transaction_Activity.class);
		startActivity(intent);
//		finish(); //do not finish because transaction_Activity won't recieve account object
	}
	public void onCancel(View v){
		finish();
	}

}
