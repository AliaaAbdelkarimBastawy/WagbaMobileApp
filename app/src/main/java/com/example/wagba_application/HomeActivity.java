package com.example.wagba_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
FirebaseAuth  mAuth;
Intent getUserInfo;
String email;
String password;
UniversalUserDatabase DB;
SharedPreferences settings;
SharedPreferences.Editor editor;
public static final String PREFS_NAME = "MyPreferenceFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();

        getSupportActionBar().setElevation(0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
            replaceFragment(new Home());
            getUserInfo = getIntent();
        settings = getSharedPreferences(PREFS_NAME,0);

        email = settings.getString("Email", "Email not found");
        password = settings.getString("Password", "Password not found");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null)
        {
            startActivity(new Intent(HomeActivity.this,
                    LoginActivity.class));
        }
    }

    private  BottomNavigationView.OnNavigationItemSelectedListener
            navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch(item.getItemId())
            {
                case R.id.restaurant:
                    selectedFragment = new Home();
                    break;
                case R.id.order:
                    selectedFragment = new order();
                    break;
                case R.id.trackorder:
                    selectedFragment = new trackorder();
                    break;
                case R.id.account:
                    System.out.println("HOME ACTIVITY");
                    System.out.println(email);
                    System.out.println(password);
                    selectedFragment = new account(email,password);
                    break;
            }

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, selectedFragment).commit();

            return true;
        }
    };

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }


}

