package com.example.Mrs_Potato_Updated;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author Ruby
 *
 */
public class Accountactivity extends Activity {
    
    /**
     * 
     */
    private static Account currentAccount;
    
    /**
     * 
     */
    private String accountName;
    
    /**
     * 
     */
    private static double balance;
    
    /**
     * 
     */
    private RelativeLayout lView;
    
    /**
     * 
     */
    private TextView accName;
    
    /**
     * 
     */
    private TextView bal;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_);
        currentAccount = Profile_Activity.getCurrentAccount();
        balance = currentAccount.getBalance();
        currentAccount.setBalance(balance);
        // //Textviews to show up on xml page
        lView = (RelativeLayout) findViewById(R.id.relativeLayout);
        bal = new TextView(this);
        bal.setX(122);
        bal.setY(430);
        bal.setText("Current Balance: $" + balance);
        lView.addView(bal);
    }

    @SuppressLint("NewApi")
    @Override
    public void onResume() {
        super.onResume();
        lView = (RelativeLayout) findViewById(R.id.relativeLayout);
        currentAccount = Profile_Activity.getCurrentAccount();
        accountName = currentAccount.getName();
        balance = currentAccount.getBalance();
        accName = new TextView(this);
        accName.setX(142);
        accName.setY(350);
        accName.setText("Account Name: " + accountName);
        bal.setText("Current Balance: $" + balance);
        currentAccount.setBalance(balance);

        lView.addView(accName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.account, menu);
        return true;
    }

    /**
     * 
     * @param v view
     */
    public void onAddTransaction(View v) {
        try {
            Intent intent = new Intent(this, Transaction_Activity.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Can't add Transaction ",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 
     * @param v view
     */
    public void onCancel(View v) {
        finish();
    }

    /**
     * 
     * @return balance
     */
    public static double getBalance() {
        return balance;
    }
    /**
     * 
     * @return Account
     */
    public static Account getCurrentAccount() {
        return currentAccount;
    }
}
