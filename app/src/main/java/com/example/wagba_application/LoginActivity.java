package com.example.wagba_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

Button loginBtn;
Intent intent03;
TextView textView10;
Intent intent;
FirebaseAuth mAuth;
EditText emailInput, passwordInput,confirmPassword;
Intent intentAccount;
Intent getDbFromIntent;
SharedPreferences settings;
SharedPreferences.Editor editor;
public static final String PREFS_NAME = "MyPreferenceFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setElevation(0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         settings = getSharedPreferences(PREFS_NAME,0);
         editor = settings.edit();
        emailInput = findViewById(R.id.CollegeIDEditText);
        passwordInput = findViewById(R.id.NumberEditText);
        loginBtn = findViewById(R.id.Updatebtn);
        mAuth = FirebaseAuth.getInstance();
        getDbFromIntent = getIntent();

        textView10 = findViewById(R.id.textView10);
        textView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        loginBtn = findViewById(R.id.Updatebtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser(){
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (TextUtils.isEmpty(email)){
            emailInput.setError("Email cannot be empty");
            emailInput.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            passwordInput.setError("Password cannot be empty");
            passwordInput.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).
                    addOnCompleteListener
                            (new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        intentAccount = new Intent(LoginActivity.this, HomeActivity.class);
                        editor.putString("Email", emailInput.getText().toString());
                        editor.putString("Password", passwordInput.getText().toString());
                        editor.commit();
                        SharedPreferences settings = getSharedPreferences(PREFS_NAME,0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putInt("Counter", 1);
                        editor.commit();
                        intentAccount.putExtra("Email", emailInput.getText().toString());
                        intentAccount.putExtra("Password", passwordInput.getText().toString());
                        startActivity(intentAccount);
                    }else{
                        Toast.makeText(
                                LoginActivity.this,
                                "Log in Error: " +
                                        task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
        });
        }
    }
}
