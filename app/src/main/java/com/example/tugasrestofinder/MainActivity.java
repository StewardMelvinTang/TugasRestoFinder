package com.example.tugasrestofinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageButton imgbtn_profile;
    TextView txt_discover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txt_discover = (TextView) findViewById(R.id.txt_discoveryour);
        imgbtn_profile = (ImageButton) findViewById(R.id.imgbtn_profile);

        imgbtn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextactivity=new Intent(getBaseContext(), Profile.class);
                startActivity(nextactivity);

            }
        });




    }
}