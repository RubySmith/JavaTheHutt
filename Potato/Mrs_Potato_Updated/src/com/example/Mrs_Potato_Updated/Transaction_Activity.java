package com.example.Mrs_Potato_Updated;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Transaction_Activity extends Activity {

	private EditText am;
	private EditText cat;
	private static Account currentAccount;
	private static Profile currentProfile;
	private GregorianCalendar userTime;
	private static Transaction transaction = new Transaction(0, false, null, null); //initialize transaction object to be sent
	private DataBaseHandler db=new DataBaseHandler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction_);
		cat = (EditText)findViewById(R.id.editText1);
		am = (EditText)findViewById(R.id.editText2);
		TimeZone tz = TimeZone.getTimeZone("EST"); //eastern time zone, not working
		userTime = new GregorianCalendar(tz);
		Date date = userTime.getTime();
	    Calendar cal = Calendar.getInstance(tz);
	    cal.setTime(date);
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH);
	    int day = cal.get(Calendar.DAY_OF_MONTH);
	    userTime.set(year, month, day);
		currentAccount = Profile_Activity.getCurrentAccount();
		currentProfile = Profile_Activity.getCurrentProfile();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transaction_, menu);
		return true;
	}
	public void onDeposit(View v){
		try{
			String amount = am.getText().toString();
			String category = cat.getText().toString();
			double AMOUNT = Double.valueOf(amount);
			//add transaction to spending report
			transaction = new Transaction(AMOUNT, true, category, userTime); //leaving name and sysTime null
			db.addTransaction(currentProfile, currentAccount, transaction); // error in this line
			//change balance in account
			double c =currentAccount.getBalance();
			c = c + AMOUNT;
			currentAccount.setBalance(c);
			finish();
		}catch (Exception e){
			Log.d("Error", e.toString());
		}
	}
	public void onWithdraw(View v){
		try{
			String amount = am.getText().toString();
			double AMOUNT = Double.valueOf(amount);
			String category = cat.getText().toString();
			//add transaction to spending report
			transaction = new Transaction(AMOUNT, false, category, userTime);
			db.addTransaction(currentProfile, currentAccount, transaction);
			//change balance in account
			double c =currentAccount.getBalance();
			c = c - AMOUNT;
			currentAccount.setBalance(c);
			finish();
		}catch(Exception e){
			Log.d("Error", e.toString());
		}
	}
	
	public static Transaction getTransaction(){
		return transaction;
	}
	
	public void onCancel(View v){
		finish();
	}
	
	//for testing
	public void setCurrentProfile(Profile p){
		currentProfile = p;
	}
	public void setCurrentAccount(Account a){
		currentAccount = a;
	}
}
