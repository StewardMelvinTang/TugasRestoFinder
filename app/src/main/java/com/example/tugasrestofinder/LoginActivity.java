package com.example.tugasrestofinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText tb_email, tb_password;
    Button btn_masuk;
    ImageButton imgbtn_google, imgbtn_facebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_masuk = findViewById(R.id.btn_masuk);
        imgbtn_google = findViewById(R.id.btn_google);
        imgbtn_facebook = findViewById(R.id.btn_facebook);
        tb_email = findViewById(R.id.tb_email);
        tb_password = findViewById(R.id.tb_password);

        btn_masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = tb_email.getText().toString();
                String pw = tb_password.getText().toString();

                if (email.equals("admin@gmail.com") && pw.equals("1234")) {
                    Intent intentkefav = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intentkefav);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this,"Email atau password salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imgbtn_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"));
                    startActivity(myIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(LoginActivity.this, "No application can handle this request."
                            + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
        imgbtn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                    startActivity(myIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(LoginActivity.this, "No application can handle this request."
                            + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        //finish
    }
}