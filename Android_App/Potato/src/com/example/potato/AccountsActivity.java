package com.example.potato;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AccountsActivity extends ListActivity {
	
	
	ArrayList<String> accountNames=new ArrayList<String>();
	ListView lv;
	ArrayList<Account> accounts;
	HashMap<String, Account> map;
	protected static String reportType;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accounts);
	}
	
	
	@Override
	protected void onResume(){
		super.onResume();
		generateList();
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		TextView txt=(TextView) v;
		Intent intent;
		if (txt.getText().toString().equals("Create New Account")){
			intent = new Intent(this, CreateActivity.class);
		}
		else if (txt.getText().toString().equals("Generate Spending Report")){
			reportType = "Spending";
			Log.d("Debug" , "In spending report in AccountsActivity");
			intent= new Intent(AccountsActivity.this, DateRangeSelectionActivity.class);
		}
		else if (txt.getText().toString().equals("Generate Source Report")){
			reportType = "Source";
			intent= new Intent(this, DateRangeSelectionActivity.class);
		}
		else if (txt.getText().toString().equals("Generate Cash FLow Report")){
			reportType = "CashFlow";
			intent= new Intent(this, DateRangeSelectionActivity.class);
		}
		else if (txt.getText().toString().equals("Generate Account Report")){
			reportType = "Account";
			intent= new Intent(this, DateRangeSelectionActivity.class);
		}
		else if (txt.getText().toString().equals("Generate Transaction History Report")){
			reportType = "TransactionHistory";
			intent= new Intent(this, DateRangeSelectionActivity.class);
		}
		else{
			//Log.d("debug", ""+txt.getText());
			Current.setAccount(map.get(""+txt.getText()));
			intent = new Intent(this, AccountActivity.class);
		}
		startActivity(intent);
	}
	
	public void onClickCreateAccount(View v){
		Intent intent = new Intent(this, CreateActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.accounts, menu);
		return true;
	}
	
	private void generateList(){
		lv=getListView();
		map=new HashMap<String, Account>();
		try{
			Log.d("Debug", Current.getProfile().getUsername());
		}catch(NullPointerException e){
			Log.d("Debug", "NULL!!");
		}
		
		accounts=(ArrayList<Account>)Current.getProfile().getAccounts();

		for (Account a:accounts){
			map.put(a.toString(), a);
		}
		if (map.isEmpty()){
			accountNames.add("Create New Account");
		}
		else{
			accountNames.clear();
			accountNames.addAll( map.keySet());
			accountNames.add("Create New Account");
			accountNames.add("Generate Spending Report");
//			accountNames.add("Generate Category Report");
//			accountNames.add("Generate Source Report");
//			accountNames.add("Generate Cash FLow Report");
//			accountNames.add("Generate Transaction History Report");
		}
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, accountNames);
		lv.setAdapter(adapter);
	}

}
