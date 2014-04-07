package com.example.Mrs_Potato_Updated;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

/**
 * 
 * @author Ruby
 *
 */
public class Transactionactivity extends Activity {

    /**
     * private instance variable.
     */
    private EditText am;
    
    /**
     * private instance variable.
     */
    private EditText cat;
    
    /**
     * private static instance variable.
     */
    private static Account currentAccount;
    
    /**
     * private static instance variable.
     */
    private static Profile currentProfile;
    
    /**
     * private instance variable.
     */
    private GregorianCalendar userTime;
    
    /**
     * private static instance variable.
     */
    private static Transaction transaction = new Transaction(0, false, null,
            null);
    
    /**
     * private instance variable.
     */
    private DataBaseHandler db = new DataBaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_);
        cat = (EditText) findViewById(R.id.editText1);
        am = (EditText) findViewById(R.id.editText2);
        TimeZone tz = TimeZone.getTimeZone("EST"); // eastern time zone, not
                                                   // working
        userTime = new GregorianCalendar(tz);
        Date date = userTime.getTime();
        Calendar cal = Calendar.getInstance(tz);
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        userTime.set(year, month, day);
        currentAccount = Profileactivity.getCurrentAccount();
        currentProfile = Profileactivity.getCurrentProfile();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.transaction_, menu);
        return true;
    }

    /**
     * Calculates value after depositing amount and then finishes current activity and returns to Account Activity.
     * @param v view
     */
    public void onDeposit(View v) {
        try {
            String amount = am.getText().toString();
            String category = cat.getText().toString();
            double amountA = Double.valueOf(amount);
            // add transaction to spending report
            transaction = new Transaction(amountA, true, category, userTime); // leaving
                                                                             // name
                                                                             // and
                                                                             // sysTime
                                                                             // null
            db.addTransaction(currentProfile, currentAccount, transaction); // error
                                                                            // in
                                                                            // this
                                                                            // line
            // change balance in account
            double c = currentAccount.getBalance();
            c = c + amountA;
            currentAccount.setBalance(c);
            finish();
        } catch (NumberFormatException e) {
            Log.d("Error", e.toString());
        }
    }

    /**
     * Calculates value after withdrawing amount and then finishes current activity and returns to Account Activity.
     * @param v view
     */
    public void onWithdraw(View v) {
        try {
            String amount = am.getText().toString();
            double amountA = Double.valueOf(amount);
            String category = cat.getText().toString();
            // add transaction to spending report
            transaction = new Transaction(amountA, false, category, userTime);
            db.addTransaction(currentProfile, currentAccount, transaction);
            // change balance in account
            double c = currentAccount.getBalance();
            c = c - amountA;
            currentAccount.setBalance(c);
            finish();
        } catch (NumberFormatException e) {
            Log.d("Error", e.toString());
        }
    }

    /**
     * Returns the transaction.
     * @return transaction
     */
    public static Transaction getTransaction() {
        return transaction;
    }

    /**
     * Finishes the current activity, Transaction Activity.
     * @param v view
     */
    public void onCancel(View v) {
        finish();
    }


    /**
     * Sets the currentProfile to p.
     * @param p profile
     */
    public void setCurrentProfile(Profile p) {
        currentProfile = p;
    }

    /**
     * Sets the currentAccount to a.
     * @param a amount
     */
    public void setCurrentAccount(Account a) {
        currentAccount = a;
    }
}