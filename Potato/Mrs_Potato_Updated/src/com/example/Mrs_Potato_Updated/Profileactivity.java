package com.example.Mrs_Potato_Updated;

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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * 
 * @author Ruby
 *
 */
public class Profileactivity extends Activity {
    
    /**
     * private instance variable name.
     */
    private static Profile currentProfile;
    
    /**
     * private instance variable name.
     */
    private static Account currentAccount;
    
    /**
     * private instance variable name.
     */
    private TextView greeting = null;
    
    /**
     * private instance variable name.
     */
    private ArrayList<Account> accounts;
    
    /**
     * private instance variable name.
     */
    private DataBaseHandler db = new DataBaseHandler(this);
    
    /**
     * private instance variable name.
     */
    private ListView listview;
    
    /**
     * private instance variable name.
     */
    private ArrayAdapter<String> adapter;
    
    /**
     * private instance variable name.
     */
    private ArrayList<String> accountNames = new ArrayList<String>();
    
    /**
     * private instance variable name.
     */
    private HashMap<String, Account> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        map = new HashMap<String, Account>();
        currentProfile = Loginactivity.getProfile();
        if (currentProfile == null) {
            currentProfile = Registeractivity.getCurrentProfile();
        }
        accounts = db.getAccounts(currentProfile);
        // greeting user, dynamically display content to xml page
        RelativeLayout lView = (RelativeLayout) findViewById(R.id.relativeLayout);
        greeting = new TextView(this);
        greeting.setText("Hi " + currentProfile.getUsername());
        lView.addView(greeting);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (currentAccount != null) { // after actually clicking on an account,
                                      // saves current balance by defining the
                                      // current account
            currentAccount = Accountactivity.getCurrentAccount();
            currentAccount.setBalance(Accountactivity.getBalance());
        }
        // generating accounts
        accounts = db.getAccounts(currentProfile);
        map = new HashMap<String, Account>();
        listview = (ListView) findViewById(R.id.listView1);
        accountNames.clear();
        for (int i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);
            accountNames.add(acc.getName());
        }
        // putting account names with accounts for onListClick
        for (Account a : accounts) {
            map.put(a.getName(), a);
            if (currentAccount != null
                    && a.getName().equals(currentAccount.getName())) {
                // currentBalance
                a.setBalance(currentAccount.getBalance());
            }
        }
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, accountNames);
        adapter.notifyDataSetChanged();
        listview.setAdapter(adapter);
        // /calling listener for each account
        listview.setOnItemClickListener(mMessageClickedHandler);
    }

    /**
     * Anonymous class for handling each account in listview.
     */
    // Create a message handling object as an anonymous class.
    private OnItemClickListener mMessageClickedHandler = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View v, int position,
                long id) {
            // Do something in response to the click
            accounts = db.getAccounts(currentProfile);
            String selectedFromList = (String) (listview
                    .getItemAtPosition(position)); // name of account selected
            currentAccount = map.get(selectedFromList); // account being clicked
            Intent intent = new Intent(Profileactivity.this,
                    Accountactivity.class);
            Profileactivity.this.startActivity(intent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile_, menu);
        return true;
    }

    /**
     * Returns the current profile.
     * @return profile
     */
    public static Profile getCurrentProfile() {
        return currentProfile;
    }

    /**
     * Returns the current account.
     * @return account
     */
    public static Account getCurrentAccount() { // send this to AccountActvity
                                                // so account data can be
                                                // displayed
        return currentAccount;
    }

    /**
     * Starts new intent of RegisterAccount activity and displays on screen.
     * @param v view
     */
    public void onAddAccount(View v) {
        Intent intent = new Intent(this, Registeraccountactivity.class);
        startActivity(intent);
    }

    /**
     * Starts new intent of SpendingReport activity and displays on screen.
     * @param v view
     */
    public void onGenerateSpendingReport(View v) {
        try {
            Intent intent = new Intent(this, Spendingreportactivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Log.d("Error", e.toString());
        }
    }

    /**
     * Finishes current activity().
     * @param v view
     */
    public void onLogOut(View v) {
        finish();
    }

}