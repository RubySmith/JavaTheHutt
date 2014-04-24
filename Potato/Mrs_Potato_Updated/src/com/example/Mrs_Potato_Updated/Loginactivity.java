package com.example.Mrs_Potato_Updated;

import java.io.IOException;

import com.example.Mrs_Potato_Updated.DataBaseHandler.InvalidPasswordException;
import com.example.Mrs_Potato_Updated.DataBaseHandler.InvalidUserException;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
/**
 * 
 * @author Ruby
 *
 */
public class Loginactivity extends Activity {
    
    /**
     * private instance variable name.
     */
    private EditText username;
    
    /**
     * private instance variable name.
     */
    private EditText password;
    
    /**
     * private instance variable name.
     */
    private DataBaseHandler db = new DataBaseHandler(this);
    
    /**
     * private static instance variable name.
     */
    private static Profile profile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        username = (EditText) findViewById(R.id.editText1);
        password = (EditText) findViewById(R.id.editText2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_, menu);
        return true;
    }
    
    /**
     * Finishes Login Activity.
     * @param v view
     */
    public void onCancel(View v) {
        meow("hiss.mp3");
        finish();
    }

    /**
     * Confirms user login and starts new intent of LoginActivity.
     * @param v view
     * @throws InvalidUserException checks for invalid user
     * @throws InvalidPasswordException checks for invalid password
     */
    public void onLogin(View v) throws InvalidUserException,
            InvalidPasswordException {
        try {
            meow("cat-meow2.mp3");
            String userName = username.getText().toString();
            String passWord = password.getText().toString();
            System.out.println(userName + ", " + passWord);
            profile = db.getProfile(userName, passWord);
            Intent intent = new Intent(this, Profileactivity.class);
            intent.putExtra("Title", userName);
            startActivity(intent);
            finish();
        } catch (InvalidUserException e) {
            meow("hiss.mp3");
            Toast.makeText(getApplicationContext(), "Invalid Username ",
                    Toast.LENGTH_SHORT).show();
        } catch (InvalidPasswordException e) {
            meow("hiss.mp3");
            Toast.makeText(getApplicationContext(), "Invalid Password",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Returns the currentProfile.
     * @return p
     */
    public static Profile getProfile() {
        return profile;
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