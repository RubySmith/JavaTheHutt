package com.example.potato;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class AccountActivity extends Activity {
	
	Account acnt=Current.getAccount();
	ArrayList<Transaction> trans;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		TableLayout tbl = (TableLayout)findViewById(R.id.TBL);
		TableRow header = new TableRow(this);
		TextView hdate=new TextView(this);
		TextView hcat=new TextView(this);
		TextView hamnt=new TextView(this); 
		hdate.setText("Date");
		hcat.setText("Category");
		hamnt.setText("Amount");
		header.addView(hdate);
		header.addView(hcat);
		header.addView(hamnt);
		tbl.addView(header);
		if (acnt!=null&& acnt.getTransactions()!=null){
			trans=(ArrayList<Transaction>)acnt.getTransactions();
			for (Transaction t: trans){
				TableRow newRow = new TableRow(this);
				TextView date=new TextView(this);
				TextView cat=new TextView(this);
				TextView amnt=new TextView(this);
				amnt.setText(""+t.getAmmount());
				cat.setText(t.getCategory());
				date.setText(t.getDateString());
				Log.d("Debug", "In AccountActivity: "+t.getDateString());
				newRow.addView(date);
				newRow.addView(cat);
				newRow.addView(amnt);
				tbl.addView(newRow);
			}
		
			TableRow footer = new TableRow(this);
			TextView fdate=new TextView(this);
			TextView fcat=new TextView(this);
			TextView famnt=new TextView(this); 
			fdate.setText("Current");
			fcat.setText("Balance");
			famnt.setText(""+acnt.getTotal());
			footer.addView(fdate);
			footer.addView(fcat);
			footer.addView(famnt);
			tbl.addView(footer);
		}
	}
	
	public void onClickAddTransaction(View v){
		Intent intent = new Intent(this, TransactionActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account, menu);
		return true;
	}
	

}
