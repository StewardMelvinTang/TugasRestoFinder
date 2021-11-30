package com.example.tugasrestofinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    ImageButton imgbtn_back;
    Button btn_favorite, btn_editprofile;
    TextView txt_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imgbtn_back = (ImageButton) findViewById (R.id.imgbtn_back);
        btn_favorite = (Button) findViewById (R.id.btn_favorite);
        btn_editprofile = (Button) findViewById (R.id.btn_editprofile);
        txt_username = (TextView) findViewById (R.id.txt_username);

        imgbtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextactivity = new Intent(getBaseContext(), EditProfile.class);
                startActivity(nextactivity);
                finish();
            }
        });
        btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextactivity=new Intent(getBaseContext(), FavoriteList.class);
                startActivity(nextactivity);
            }
        });

    }}
