package com.example.Mrs_Potato_Updated;

import java.io.IOException;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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
        meow("cat-meow2.mp3");
        Intent intent = new Intent(this, Registeractivity.class);
        startActivity(intent);
    }

    /**
     * Starts new intent of loginActivity.
     * @param v view
     */
    public void onLogin(View v) {
        meow("cat-meow2.mp3");
        Intent intent = new Intent(this, Loginactivity.class);
        startActivity(intent);
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