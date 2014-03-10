package com.example.potato;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TransactionActivity extends Activity {

	private EditText source;
	private EditText destination;
	private EditText ammount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction);
		source = (EditText)findViewById(R.id.editText1);
		destination = (EditText)findViewById(R.id.editText2);
		ammount = (EditText)findViewById(R.id.editText3);
	}
	
	public void onClickConfirm(View V){
		String src=source.getText().toString();
		String dest=destination.getText().toString();
		double amnt;
		try{
			amnt=Double.parseDouble(ammount.getText().toString());
		}catch(NumberFormatException e){
			Toast.makeText(getApplicationContext(), "Please Enter Valid Amount", Toast.LENGTH_SHORT).show();
			return;
		}
		
		Current.getAccount().addTransaction(src,dest,amnt);
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
