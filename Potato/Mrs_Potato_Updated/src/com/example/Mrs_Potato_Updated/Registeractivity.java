package com.example.Mrs_Potato_Updated;

import java.io.IOException;

import com.example.Mrs_Potato_Updated.DataBaseHandler.InvalidPasswordException;
import com.example.Mrs_Potato_Updated.DataBaseHandler.InvalidUserException;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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
public class Registeractivity extends Activity {
    /**
     *private instance variable.
     */
    private EditText username;
    
    /**
     * private instance variable.
     */
    private EditText password;
    
    /**private static instance variable.
     * 
     */
    private static Profile currentProfile = null;
    
    /**
     * private instance variable.
     */
    private DataBaseHandler db = new DataBaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        username = (EditText) findViewById(R.id.editText1);
        password = (EditText) findViewById(R.id.editText2);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.register_, menu);
        return true;
    }

    /**
     * Returns the currentProfile.
     * @return current profile
     */
    public static Profile getCurrentProfile() {
        return currentProfile;
    }
    
    /**
     *Finishes the current activity and returns to the previous activity. 
     * @param v view
     */
    public void onCancel(View v) {
        meow("hiss.mp3");
        finish();
    }

    /**
     * Confirms user registration and starts a new intent of activity RegisterActivity.
     * @param v view
     * @throws InvalidUserException checks for invalid user
     * @throws InvalidPasswordException checks for invalid password
     */
    public void onRegisterUser(View v) throws InvalidUserException,
            InvalidPasswordException {
        String userName = username.getText().toString();
        if (userName.length() < 2) {
            meow("hiss.mp3");
            Toast.makeText(getApplicationContext(), "Username too short.",
                    Toast.LENGTH_SHORT).show();
        }
        String passWord = password.getText().toString();
        if (passWord.length() < 2) {
            meow("hiss.mp3");
            Toast.makeText(getApplicationContext(), "Password too short",
                    Toast.LENGTH_SHORT).show();
        }
        if (passWord.length() >= 2 && userName.length() >= 2) {
            meow("cat-meow2.mp3");
            try {
                currentProfile = new Profile(userName, passWord);
                System.out.println(currentProfile.getUsername() + ", "
                        + currentProfile.getPassword());
                db.addProfile(currentProfile); // add to database
                Intent intent = new Intent(this, Profileactivity.class);
                startActivity(intent); // problem
                finish();
            } catch (InvalidUserException e) {
                meow("hiss.mp3");
                Toast.makeText(getApplicationContext(),
                        "Username taken. Please choose again.",
                        Toast.LENGTH_SHORT).show();
            }
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