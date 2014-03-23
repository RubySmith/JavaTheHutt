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
		if (txt.getText().toString().equals("Create New Account")){
			Intent intent = new Intent(this, CreateActivity.class);
			startActivity(intent);
		}
		else if (txt.getText().toString().equals("Generate Report")){
			Intent intent= new Intent(this, GenReportActivity.class);
			startActivity(intent);
		}
		else{
			//Log.d("debug", ""+txt.getText());
			Current.setAccount(map.get(""+txt.getText()));
			Intent intent = new Intent(this, AccountActivity.class);
			startActivity(intent);
		}
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
			accountNames.add("Generate Report");
			accountNames.add("Create New Account");
		}
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, accountNames);
		lv.setAdapter(adapter);
	}

}
