package com.example.tugasrestofinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText tb_email, tb_password;
    Button btn_masuk, btn_register;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_masuk = findViewById(R.id.btn_masuk);
        tb_email = findViewById(R.id.tb_email);
        tb_password = findViewById(R.id.tb_password);
        btn_register = findViewById(R.id.btn_register);
        fAuth = FirebaseAuth.getInstance();

        btn_masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email = tb_email.getText().toString().trim();
                String password = tb_password.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    tb_email.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    tb_password.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6 ){
                    tb_password.setError("Password must be atleast 6 letters");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(LoginActivity.this, "Logged in.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(LoginActivity.this);
                            dlgAlert.setMessage(task.getException().getMessage());
                            dlgAlert.setTitle("Error");
                            dlgAlert.setPositiveButton("OK", null);
                            dlgAlert.setCancelable(true);
                            dlgAlert.create().show();

                        }
                    }
                });


            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentkeregister = new Intent(getApplicationContext(), register_activity.class);
                startActivity(intentkeregister);
                finish();
            }
        });




        //finish
    }
}