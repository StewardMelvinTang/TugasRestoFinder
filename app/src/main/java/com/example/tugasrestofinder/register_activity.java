package com.example.tugasrestofinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register_activity extends AppCompatActivity {

    Button btn_daftar, btn_login;
    EditText et_username, et_email, et_password;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_daftar = findViewById(R.id.btn_masuk);
        btn_login = findViewById(R.id.btn_login);
        et_username = findViewById(R.id.tb_username);
        et_email = findViewById(R.id.tb_email);
        et_password = findViewById(R.id.tb_password);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentkelogin = new Intent (getApplicationContext(), LoginActivity.class);
                startActivity(intentkelogin);
                finish();
            }
        });


        //inisialisasi firebase
        FirebaseApp.initializeApp(this);
        fAuth = FirebaseAuth.getInstance();

        //jika usernya tidak null dia akan di intent ke main activity class
//        if(fAuth.getCurrentUser() !=null) {
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            finish();
//        }

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    et_email.setError("Email is Required");
                }

                if (TextUtils.isEmpty(password)) {
                    et_password.setError("Password id Required");
                }

                if (password.length() < 6){
                    et_password.setError("Password must be atleast 6 letters");
                }

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(register_activity.this, "Register success. Signing in as " + et_username.getText(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {

                            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(register_activity.this);
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



        //method
    }


    //end
}