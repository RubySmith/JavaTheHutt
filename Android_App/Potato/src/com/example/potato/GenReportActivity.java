package com.example.potato;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class GenReportActivity extends Activity {

	private String rt= AccountsActivity.reportType;
	private DatabaseHandler db=new DatabaseHandler(this);
	//private Collection transData=db.generateReport();
	ArrayList<Transaction> trans;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gen_report);
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		trans= retrieveReportData(); 
		if (rt.equals("Spending")){
			generateSpendingReport();
		}
		else if (rt.equals("Source")){
			generateSourceReport();
		}
		else if (rt.equals("CashFlow")){
			generateCashFlowReport();
		}
		else if (rt.equals("Account")){
			generateAccountReport();
		}
		else if (rt.equals("TransactionHistory")){
			generateTransHistReport();
		}
	}
	
	private ArrayList<Transaction> retrieveReportData() {
		ArrayList<Transaction> toReturn=new ArrayList<Transaction>();
		for (Account a: Current.getProfile().getAccounts()){
			toReturn.addAll(a.getTransactions());
		}
		return toReturn;
	}

	private void generateSpendingReport(){
		TableLayout tbl = (TableLayout)findViewById(R.id.TBL);
		TableRow header = new TableRow(this);
		TextView head=new TextView(this);
		head.setText("Spending Category Report for "+Current.getProfile().getUsername());
		header.addView(head);
		TableRow dateRow= new TableRow(this);
		TextView dates=new TextView(this);
		DateFormat df= DateFormat.getDateInstance();
		String start=df.format(DateRangeSelectionActivity.startDate);
		String end=df.format(DateRangeSelectionActivity.endDate);
		dates.setText(start+"-"+end);
		tbl.addView(header);
		tbl.addView(dateRow);
		HashMap <String, Double> categories=new HashMap<String, Double>();
		String category;
		for (Transaction t: trans){
			category=t.getCategory();
			if (categories.containsKey(category)){
				categories.put(category, categories.get(category)+t.getAmmount());
			}
			else{
				categories.put(category, t.getAmmount());
			}
		}
		Set<String> keys=categories.keySet();
		for (String s: keys){
			TableRow newRow = new TableRow(this);
			TextView cat=new TextView(this);
			TextView amnt=new TextView(this);
			cat.setText(s);
			amnt.setText(""+categories.get(s));
			newRow.addView(cat);
			newRow.addView(amnt);
			tbl.addView(newRow);
		}
	}

	private void generateTransHistReport() {
		
		
	}

	private void generateAccountReport() {
		
		
	}

	private void generateCashFlowReport() {
		TableLayout tbl = (TableLayout)findViewById(R.id.TBL);
		TableRow header = new TableRow(this);
		TextView head=new TextView(this);
		head.setText("Cash Flow Report");
		header.addView(head);
		TableRow dateRow= new TableRow(this);
		TextView dates=new TextView(this);
		DateFormat df= DateFormat.getDateInstance();
		String start=df.format(DateRangeSelectionActivity.startDate);
		String end=df.format(DateRangeSelectionActivity.endDate);
		dates.setText(start+"-"+end);
		tbl.addView(header);
		tbl.addView(dateRow);
		
	}

	private void generateSourceReport() {
		TableLayout tbl = (TableLayout)findViewById(R.id.TBL);
		TableRow header1 = new TableRow(this);
		TextView head1=new TextView(this);
		head1.setText("Income Source Report for "+Current.getProfile().getUsername());
		header1.addView(head1);
		TableRow dateRow= new TableRow(this);
		TextView dates=new TextView(this);
		DateFormat df= DateFormat.getDateInstance();
		String start=df.format(DateRangeSelectionActivity.startDate);
		String end=df.format(DateRangeSelectionActivity.endDate);
		dates.setText(start+"-"+end);
		tbl.addView(header1);
		tbl.addView(dateRow);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gen_report, menu);
		return true;
	}
	

}
