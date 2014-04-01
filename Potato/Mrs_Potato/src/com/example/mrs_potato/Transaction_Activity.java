package com.example.mrs_potato;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Transaction_Activity extends Activity {

	private EditText am;
	private EditText cat;
	private static Account currentAccount;
	private static Profile currentProfile;
	private GregorianCalendar userTime;
	private GregorianCalendar sysTime;
	private static Transaction transaction = new Transaction(null, 0, false, null, null, null); //initialize transaction object to be sent
	private SpendingReport sr;
	DataBaseHandler db=new DataBaseHandler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction_);
		cat = (EditText)findViewById(R.id.editText1);
		am = (EditText)findViewById(R.id.editText2);
		TimeZone tz = TimeZone.getTimeZone("EST"); //eastern time zone, not working
		userTime = new GregorianCalendar(tz);
		sysTime = new GregorianCalendar(tz);
		
		Date date = userTime.getTime();
	    Calendar cal = Calendar.getInstance(tz);
	    cal.setTime(date);
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH);
	    int day = cal.get(Calendar.DAY_OF_MONTH);
	    userTime.set(year, month, day);
		currentAccount = Profile_Activity.getCurrentAccount();
		currentProfile = Profile_Activity.currentProfile;
		sr = new SpendingReport();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transaction_, menu);
		return true;
	}
	public void onDeposit(View v){
		String amount = am.getText().toString();
		String category = cat.getText().toString();
		double AMOUNT = Double.valueOf(amount);
		//add transaction to spending report
		transaction = new Transaction(null, AMOUNT, true, category, userTime, null); //leaving name and sysTime null
		System.out.println(userTime.getTime());
		db.addTransaction(currentProfile, currentAccount, transaction);
		sr .addTransactionToList(transaction);
		System.out.println("Spendin: " + sr.getTransactionList());
		//change balance in account
		double c =currentAccount.getBalance();
		c = c + AMOUNT;
		System.out.println("DDDD: " + c);
		currentAccount.setBalance(c);
		System.out.println("CA: " +currentAccount.getName()+ ", "+  currentAccount.getBalance());
//		Intent intent = new Intent(this, Account_Activity.class);
//		startActivity(intent);
		finish();
	}
	public void onWithdraw(View v){
		String amount = am.getText().toString();
		double AMOUNT = Double.valueOf(amount);
		String category = cat.getText().toString();
		//add transaction to spending report
		transaction = new Transaction(null, AMOUNT, false, category, userTime, null);
		db.addTransaction(currentProfile, currentAccount, transaction);
		sr.addTransactionToList(transaction);
		//change balance in account
		double c =currentAccount.getBalance();
		c = c - AMOUNT;
		currentAccount.setBalance(c);
//		Intent intent = new Intent(this, Account_Activity.class);
//		startActivity(intent);
		finish();
	}
	
	public static Transaction getTransaction(){
		return transaction;
	}
	
	public void onCancel(View v){
		finish();
	}
}
