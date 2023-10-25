package com.example.wagba_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EmptyCart_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_cart);
        getSupportActionBar().setElevation(0);

    }
}