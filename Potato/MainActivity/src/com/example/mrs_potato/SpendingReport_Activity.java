package com.example.mrs_potato;

import java.util.GregorianCalendar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class SpendingReport_Activity extends Activity {
	private static Profile currentProfile;
	private DataBaseHandler db=new DataBaseHandler(this);

	private TableLayout tbl;
	private TableRow row0;
	private TableRow row1;
	private TextView TIME;
	private TextView CATEGORY;
	private TextView ACCOUNT;
	private TextView AMOUNT;
	private TextView ti;
	private TextView cat;
	private TextView acc;
	private TextView am;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spending_report_);
		//getting data from account and transaction activities
		currentProfile = Profile_Activity.currentProfile;
		
		tbl = (TableLayout)findViewById(R.id.TBL);
		row0 = new TableRow(this);
		CATEGORY = new TextView(this);
		CATEGORY.setText("                     ");
		ACCOUNT = new TextView(this);
		ACCOUNT.setText("                        ");
		AMOUNT = new TextView(this);
		AMOUNT.setText("                        ");
		TIME = new TextView(this);
		TIME.setText("                      ");
		row0 .addView(ACCOUNT);
		row0 .addView(CATEGORY);
		row0 .addView(AMOUNT);
		row0 .addView(TIME);
		tbl.addView(row0);
		
		for (Account a: db.getAccounts(currentProfile)){
			for (Transaction t : db.getTransactions(currentProfile, a)){
				String account = a.getName();
				acc = new TextView(this);
				acc.setText(account); 
				String category = t.getCategory();
				cat = new TextView(this);
				cat.setText(category);
				GregorianCalendar time = t.getUserTimeStamp();
				ti = new TextView(this);
				String timeS = "" +(time.get(GregorianCalendar.MONTH)+ "-" + time.get(GregorianCalendar.DATE) + "-" + time.get(GregorianCalendar.YEAR));
				ti.setText(timeS);
				String amount = String.valueOf(t.getAmount());
				if(!t.isDeposit())
					amount = "-" + amount;
				am = new TextView(this);
				am.setText(amount);
				
				row1 = null; //clear row for each transaction
				row1 = new TableRow(this);
				row1.addView(acc);
				row1.addView(cat);
				row1.addView(am);
				row1.addView(ti);
				tbl.addView(row1);
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.spending_report_, menu);
		return true;
	}

}
