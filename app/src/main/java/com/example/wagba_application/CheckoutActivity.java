package com.example.wagba_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class CheckoutActivity extends AppCompatActivity {
Button OkBtn;
Intent intent;
Intent mintent;
HashMap<String, Object> values = new HashMap<>();
FirebaseDatabase db;
DatabaseReference reference;

int OrderID;
private DatabaseReference mTrackRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        getSupportActionBar().setElevation(0);

        mintent = getIntent();
        OrderID = mintent.getIntExtra("OrderID", 0);
//
//        reference = db.getReferenceFromUrl("https://wagbamobileapp-default-rtdb.firebaseio.com/");
//        mTrackRef = reference.child("TrackOrder");
//        values.put("OrderID", OrderID);

        OkBtn = findViewById(R.id.OkBtn);
        OkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mTrackRef.child("Orders").updateChildren(values);
                intent = new Intent(CheckoutActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}