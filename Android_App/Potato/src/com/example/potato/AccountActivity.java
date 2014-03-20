package com.example.potato;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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
		TextView hsource=new TextView(this);
		TextView hdest=new TextView(this);
		TextView hamnt=new TextView(this); 
		hsource.setText("Source");
		hdest.setText("Destination");
		hamnt.setText("Amount");
		header.addView(hsource);
		header.addView(hdest);
		header.addView(hamnt);
		tbl.addView(header);
		trans=(ArrayList<Transaction>)acnt.getTransactions();
		for (Transaction t: trans){
			TableRow newRow = new TableRow(this);
			TextView source=new TextView(this);
			TextView dest=new TextView(this);
			TextView amnt=new TextView(this);
			amnt.setText(""+t.getAmmount());
			newRow.addView(source);
			newRow.addView(dest);
			newRow.addView(amnt);
			tbl.addView(newRow);
		}
	
		TableRow footer = new TableRow(this);
		TextView fsource=new TextView(this);
		TextView fdest=new TextView(this);
		TextView famnt=new TextView(this); 
		fsource.setText("Current");
		fdest.setText("Balance");
		famnt.setText(""+acnt.getTotal());
		footer.addView(fsource);
		footer.addView(fdest);
		footer.addView(famnt);
		tbl.addView(footer);
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
