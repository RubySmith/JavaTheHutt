package com.example.Mrs_Potato_Updated;

import java.io.IOException;

import com.example.Mrs_Potato_Updated.DataBaseHandler.InvalidAccountException;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 
 * @author Ruby
 *
 */
public class Registeraccountactivity extends Activity {
    
    /**
     * private instance variable.
     */
    private static Profile currentProfile;
    
    /**
     * private instance variable.
     */
    private EditText accName;
    
    /**
     * private instance variable.
     */
    private EditText bal;
    
    /**
     * private instance variable.
     */
    private DataBaseHandler db = new DataBaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeraccount_);
        accName = (EditText) findViewById(R.id.editText1);
        bal = (EditText) findViewById(R.id.editText2);
        currentProfile = Profileactivity.getCurrentProfile();
        System.out.println("Account " + currentProfile.getUsername());
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.registeraccount_, menu);
        return true;
    }

    /**
     * Finishes the current activity and returns to the previous activity. 
     * @param v view
     */
    public void onCancel(View v) {
        meow("hiss.mp3");
        finish();
    }

    /**
     * Confirms account registration and then finishes RegisterAccount Activity and returns to Profile Activity.
     * @param v view
     * @throws Exception exception
     */
    @SuppressLint("NewApi")
    public void onAddAccount(View v) throws Exception {
        System.out.println("LOOO");
        System.out.println("String; " + accName.getText().toString());
        if (!accName.getText().toString().isEmpty()
                && !bal.getText().toString().isEmpty()) {
            String accountName = accName.getText().toString();
            String balance = bal.getText().toString();
            double balanceD = Double.valueOf(balance);
            // checking to see correct input
            System.out.println("name: " + accountName);
            System.out.println("bal: " + balance);
            Account acc = new Account(balanceD, accountName);
            try {
                db.addAccount(currentProfile, acc);
            } catch (InvalidAccountException e) {
                Toast.makeText(getApplicationContext(),
                        "Account already exists..", Toast.LENGTH_SHORT).show();
            }
            meow("cat-meow2.mp3");
            finish();
        } else {
            meow("hiss.mp3");
            Toast.makeText(getApplicationContext(),
                    "Please enter valid Account Name and Balance.",
                    Toast.LENGTH_SHORT).show();
        }

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