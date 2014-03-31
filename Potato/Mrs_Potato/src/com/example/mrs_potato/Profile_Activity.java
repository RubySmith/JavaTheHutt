package com.example.mrs_potato;

import java.util.ArrayList;
import java.util.HashMap;




import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Profile_Activity extends Activity {
	private String title;
	public static  Profile currentProfile;
	private static Account currentAccount; //send Account obj to AccountActivity
	private TextView greeting = null;
	private  ArrayList<Account> accounts;
	private  DataBaseHandler db = new DataBaseHandler(this);
	private ListView listview;
	private ArrayAdapter<String> adapter;
	private ArrayList<String> accountNames = new ArrayList<String>();
	private HashMap<String, Account> map;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_);
		title= getIntent().getExtras().getString("Title"); // to pass Strings between Activities
		map = new HashMap<String, Account>();
		currentProfile = Login_Activity.p;
	     accounts = db.getAccounts(currentProfile);
		//greeting user, dynamically display content to xml page
		RelativeLayout lView = (RelativeLayout)findViewById(R.id.relativeLayout);
	     greeting = new TextView(this);
	     greeting.setText("Hi " + currentProfile.getUsername());
	     lView.addView(greeting);
	}
	@Override 
	public void onResume(){
		super.onResume();
		//generating accounts
	    accounts = db.getAccounts(currentProfile);
	    map = new HashMap<String, Account>();
	    listview = (ListView) findViewById(R.id.listView1);
	    accountNames.clear();
		for (int i =0 ; i < accounts.size(); i++){
		  	Account acc = accounts.get(i);
			 accountNames.add(acc.getName());
		 }
		//putting account names with accounts for onListClick
		for (Account a:accounts){
			map.put(a.getName(), a);
		}
		adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, accountNames);
		adapter.notifyDataSetChanged();
		listview.setAdapter(adapter);
		///calling listener for each account
		listview.setOnItemClickListener(mMessageClickedHandler); 
	}
	
	// Create a message handling object as an anonymous class.
	private OnItemClickListener mMessageClickedHandler = new OnItemClickListener() {
		public void onItemClick(AdapterView parent, View v, int position, long id) {
			// Do something in response to the click
			System.out.println("doodoo");
			 accounts = db.getAccounts(currentProfile);
			String selectedFromList =(String) (listview.getItemAtPosition(position)); //name of account selected
			currentAccount = map.get(selectedFromList); //account being clicked
			Intent intent = new Intent(Profile_Activity.this, Account_Activity.class);
			Profile_Activity.this.startActivity(intent);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile_, menu);
		return true;
	}
	
	public Profile getCurrentProfile(){
		return currentProfile;
	}
	
	public static Account getCurrentAccount(){ //send this to AccountActvity so account data can be displayed
		return currentAccount;
	}
	
	public void onAddAccount(View v){
		Intent intent = new Intent(this, RegisterAccount_Activity.class);
		startActivity(intent);		
	}

}
