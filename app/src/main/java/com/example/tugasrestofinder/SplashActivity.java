package com.example.tugasrestofinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //splash screen
        Thread splash = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 3 seconds
                    sleep(3*1000);
                    Intent nextactivity=new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(nextactivity);
                    finish();
                } catch (Exception e) {
                }
            }
        };

        splash.start();


    }
}