package com.example.potato;

import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class TransactionActivity extends Activity {

	private EditText categoryEntry;
	private DatePicker dateEntry;
	private EditText amountEntry;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction);
		dateEntry = (DatePicker)findViewById(R.id.dateEntry);
		categoryEntry = (EditText)findViewById(R.id.categoryEntry);
		amountEntry = (EditText)findViewById(R.id.amountEntry);
		
	}
	
	public void onClickConfirm(View V){
		String category=categoryEntry.getText().toString();
		
		int day = dateEntry.getDayOfMonth();
		int month = dateEntry.getMonth();
		int year = dateEntry.getYear()-1900;
		Log.d("Debug", "In Transaction Activity: "+year);
		//We need to make sure the inputs are valid
		
		@SuppressWarnings("deprecation")
		Date date = new Date(year,month,day);
		double amount = Double.parseDouble(amountEntry.getText().toString());
		try{
			amount=Double.parseDouble(amountEntry.getText().toString());
		}catch(NumberFormatException e){
			Toast.makeText(getApplicationContext(), "Please Enter Valid Amount", Toast.LENGTH_SHORT).show();
			return;
		}
		
		Current.getAccount().addTransaction(category,amount,date);
		
		Intent intent = new Intent(this, AccountActivity.class);
		startActivity(intent);
		finish();
	}
	
	public void onClickCancel(View v){
		Intent intent = new Intent(this, AccountActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transaction, menu);
		return true;
	}

}
