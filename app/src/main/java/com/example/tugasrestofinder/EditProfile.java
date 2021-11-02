package com.example.tugasrestofinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class EditProfile extends AppCompatActivity {

    ImageButton imgbtn_back;
    Button btn_simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        imgbtn_back = (ImageButton) findViewById(R.id.imgbtn_back);
        btn_simpan = (Button) findViewById(R.id.btn_simpan);

        imgbtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save here
                finish();
            }
        });
    }
}