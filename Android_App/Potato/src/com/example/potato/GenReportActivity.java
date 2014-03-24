package com.example.potato;

import java.util.Collection;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GenReportActivity extends Activity {

	private String rt= DateRangeSelectionActivity.reportType;
	private DatabaseHandler db=new DatabaseHandler(this);
	//private Collection transData=db.generateReport();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gen_report);
	}
	
	@Override
	protected void onResume(){
		super.onResume();
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
	
	private void generateSpendingReport(){
		
	}

	private void generateTransHistReport() {
		// TODO Auto-generated method stub
		
	}

	private void generateAccountReport() {
		// TODO Auto-generated method stub
		
	}

	private void generateCashFlowReport() {
		// TODO Auto-generated method stub
		
	}

	private void generateSourceReport() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gen_report, menu);
		return true;
	}
	

}
