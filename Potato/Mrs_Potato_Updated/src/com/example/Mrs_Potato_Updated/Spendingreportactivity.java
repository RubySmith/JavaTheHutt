package com.example.Mrs_Potato_Updated;

import java.util.GregorianCalendar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * 
 * @author Ruby
 *
 */
public class Spendingreportactivity extends Activity {
    
    /**
     * private static instance variable.
     */
    private static Profile currentProfile;
    
    /**
     * private instance variable.
     */
    private DataBaseHandler db = new DataBaseHandler(this);
    
    /**
     * private instance variable.
     */
    private TableLayout tbl;
    
    /**
     * private instance variable.
     */
    private TableRow row0;
    
    /**
     * private instance variable.
     */
    private TableRow row1;
    
    /**
     * private instance variable.
     */
    private TextView time;
    
    /**
     * private instance variable.
     */
    private TextView category;
    
    /**
     * private instance variable.
     */
    private TextView account;
    
    /**
     * private instance variable.
     */
    private TextView amount;
    
    /**
     * private instance variable.
     */
    private TextView ti;
    
    /**
     * private instance variable.
     */
    private TextView cat;
    
    /**
     * private instance variable.
     */
    private TextView acc;
    
    /**
     * private instance variable.
     */
    private TextView am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending_report_);
        // getting data from account and transaction activities
        currentProfile = Profileactivity.getCurrentProfile();
        tbl = (TableLayout) findViewById(R.id.TBL);
        row0 = new TableRow(this);
        category = new TextView(this);
        category.setText("                     ");
        account = new TextView(this);
        account.setText("                        ");
        amount = new TextView(this);
        amount.setText("                        ");
        time = new TextView(this);
        time.setText("                      ");
        row0.addView(account);
        row0.addView(category);
        row0.addView(amount);
        row0.addView(time);
        tbl.addView(row0);
        for (Account a : db.getAccounts(currentProfile)) {
            for (Transaction t : db.getTransactions(currentProfile, a)) {
                String anAccount = a.getName();
                acc = new TextView(this);
                acc.setText(anAccount);
                String aCategory = t.getCategory();
                cat = new TextView(this);
                cat.setText(aCategory);
                GregorianCalendar aTime = t.getUserTimeStamp();
                ti = new TextView(this);
                String timeS = ""
                        + (aTime.get(GregorianCalendar.MONTH) + "-"
                                + aTime.get(GregorianCalendar.DATE) + "-" + aTime
                                    .get(GregorianCalendar.YEAR));
                ti.setText(timeS);
                String anAmount = String.valueOf(t.getAmount());
                if (!t.isDeposit()) {
                    anAmount = "-" + anAmount;
                }
                am = new TextView(this);
                am.setText(anAmount);

                row1 = null; // clear row for each transaction
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