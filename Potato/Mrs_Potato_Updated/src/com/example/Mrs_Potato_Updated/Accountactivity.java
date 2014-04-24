package com.example.Mrs_Potato_Updated;

import java.io.IOException;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
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
     * private instance variable currentAccount.
     */
    private static Account currentAccount;
    
    /**
     * private instance variable accountName.
     */
    private String accountName;
    
    /**
     * private instance variable balance.
     */
    private static double balance;
    
    /**
     * private instance variable lView.
     */
    private RelativeLayout lView;
    
    /**
     * private instance variable accName.
     */
    private TextView accName;
    
    /**
     * private instance variable bal.
     */
    private TextView bal;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_);
        currentAccount = Profileactivity.getCurrentAccount();
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
        currentAccount = Profileactivity.getCurrentAccount();
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
     * Starts new intent of TransactionActivity.
     * @param v view
     */
    public void onAddTransaction(View v) {
        meow("cat-meow2.mp3");
        Intent intent = new Intent(this, Transactionactivity.class);
        startActivity(intent);
    }

    /**
     * Finishes current activity and takes screen back to ProfileActivity.
     * @param v view
     */
    public void onCancel(View v) {
        meow("hiss.mp3");
        finish();
    }

    /**
     * Returns the account balance.
     * @return balance
     */
    public static double getBalance() {
        return balance;
    }
    /**
     * Returns the current account.
     * @return Account
     */
    public static Account getCurrentAccount() {
        return currentAccount;
    }
    private void meow(String type) {
        final MediaPlayer mp = new MediaPlayer();
        if(mp.isPlaying())
        {  
            mp.stop();
            mp.reset();
        } 
        try {
    
            AssetFileDescriptor afd;
            afd = getAssets().openFd(type);
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.prepare();
            mp.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}