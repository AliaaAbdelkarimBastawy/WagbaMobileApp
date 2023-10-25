package com.example.wagba_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {
    static int MAXLOGINATTEMPTS = 5;
    static String TAG = "USERLOGINFO";

    UniversalUserDatabase_3 uniDB;
    UniversalDao_3 uniDao;
    User_3 currentUser = new User_3();
    int loginAttempts = 0;
    EditText etRegEmail;
    EditText etRegPassword;
    EditText etRegNumber;
    EditText etRegCollegeID;
    TextView tvLoginHere;
    Button btnRegister;

    FirebaseAuth mAuth;
    EditText etRefName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etRefName = findViewById(R.id.Username);
        etRegEmail = findViewById(R.id.CollegeIDEditText);
        etRegPassword = findViewById(R.id.NumberEditText);
        tvLoginHere = findViewById(R.id.textView10);
        btnRegister = findViewById(R.id.Signbtn);
        etRegNumber = findViewById(R.id.Number);
        etRegCollegeID = findViewById(R.id.CollegeID);
        mAuth = FirebaseAuth.getInstance();

        uniDB = UniversalUserDatabase_3.getInstance(this);
        uniDao = uniDB.getAllDao();

        btnRegister.setOnClickListener(view ->{
            createUser();
            System.out.println("BA2OLKKKKKK");
            uniDao.insertUser(new User_3(etRegEmail.getText().toString(),
                    etRegPassword.getText().toString(),
                    etRegNumber.getText().toString(),
                    etRefName.getText().toString(),
                    etRegCollegeID.getText().toString()));
            // Login to the first user logging the login attempt
            if (forceLogin(etRegEmail.getText().toString(),etRegPassword.getText().toString(),true)) {
                Log.d(TAG,currentUser.userName + " successfully logged in.");
                uniDao.insertLog(new UserLog_3(currentUser,"Logged In Successfully"));

            }
            // Write the logs to the log for Universal and userOnly approach
            logCurrentUserLog();
        });

        tvLoginHere.setOnClickListener(view ->{
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
        });
    }


    private boolean login(String userName, String password) {
        long userId;
        if (++loginAttempts >= MAXLOGINATTEMPTS) return false;
        if (!((userId = uniDao.verifyUserLogin(userName,password)) > 0)) return false;
        currentUser = uniDao.getUserById(userId);
        loginAttempts = 0;
        return true;
    }
    private boolean forceLogin(String userName, String password, boolean crashIfTooManyAttempts) {
        while (!login(userName,password)) {
            if (loginAttempts >= MAXLOGINATTEMPTS) {
                if (crashIfTooManyAttempts)
                    throw new RuntimeException("Too Many Login Attempts - Goodbye");
                return false;
            }
        }
        return true;
    }
    private void logCurrentUserLog() {
        for(UserLog_3 ul: uniDao.getUserLogs(currentUser.getUserId())) {
            Log.d(TAG,ul.timestamp + " User = " + currentUser.userName + "Log = " + ul.logData);
        }
    }




    public static boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    private void createUser(){
        String email = etRegEmail.getText().toString();
        String password = etRegPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            etRegEmail.setError("Email cannot be empty");
            etRegEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etRegPassword.setError("Password cannot be empty");
            etRegPassword.requestFocus();

        }else {
            if (isValidEmail(email)) {

                if(password.length() >= 8) {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                            } else {
                                Toast.makeText(SignUpActivity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

                else{
                    etRegPassword.setError("Password must be at least 8 characters");
                    etRegPassword.requestFocus();
                }

            }

            else
            {
                if (password.length() <8)
                {
                    etRegEmail.setError("Email is not valid");
                    etRegEmail.requestFocus();
                    etRegPassword.setError("Password must be at least 8 characters");
                    etRegPassword.requestFocus();
                }
                else
                {
                    etRegEmail.setError("Email is not valid");
                    etRegEmail.requestFocus();
                }

            }
        }
    }
}