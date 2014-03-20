package com.example.potato;

import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TransactionActivity extends Activity {

	private EditText categoryEntry;
	private EditText dateEntry;
	private EditText amountEntry;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction);
		dateEntry = (EditText)findViewById(R.id.dateEntry);
		categoryEntry = (EditText)findViewById(R.id.categoryEntry);
		amountEntry = (EditText)findViewById(R.id.amountEntry);
	}
	
	public void onClickConfirm(View V){
		String category=categoryEntry.getText().toString();
		Date date = (Date) dateEntry.getText();
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
