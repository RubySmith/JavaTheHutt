package com.example.Mrs_Potato_Updated;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

/**
 * Main activity class.
 * 
 * @author Ruby Smith
 *
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /**
     * Starts new intent of Register Activity.
     * @param v view
     */
    public void onRegister(View v) {
        Intent intent = new Intent(this, Registeractivity.class);
        startActivity(intent);
    }

    /**
     * Starts new intent of loginActivity.
     * @param v view
     */
    public void onLogin(View v) {
        Intent intent = new Intent(this, Loginactivity.class);
        startActivity(intent);
    }

}