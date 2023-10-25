package com.example.wagba_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.ZoneId;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class PlaceYourOrder_Activity extends AppCompatActivity {
    Button PayBtn;
    Intent intent;
    RadioButton RadioButton1;
    RadioButton RadioButton2;
    Timer timer;
    RadioButton RadioButton3;
    RadioButton RadioButton4;
    String SelectedGate;
    String SelectedTime;
    EditText CardNumber;
    FirebaseDatabase db;
    DatabaseReference reference;
    ArrayList<CharSequence> IntentGetCartList;
    HashMap<String, Object> values = new HashMap<>();
    HashMap<String, Object> valuesOfTrack = new HashMap<>();
    ArrayList<Cart> CartList = new ArrayList<>();
    ArrayList<Integer> Before10AMList = new ArrayList<>();
    ArrayList<Integer> Before1PMList = new ArrayList<>();
    FirebaseDatabase dbTrack;
    DatabaseReference referenceTrack;
    Intent mIntent;
    int intValue;
    int ID;
    String Status;
    String SubTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Before10AMList.add(6);
        Before10AMList.add(7);
        Before10AMList.add(8);
        Before10AMList.add(9);

        Before1PMList.add(6);
        Before1PMList.add(7);
        Before1PMList.add(8);
        Before1PMList.add(9);
        Before1PMList.add(10);
        Before1PMList.add(11);
        Before1PMList.add(12);
        ///delete them
        Before1PMList.add(1);
        Before1PMList.add(2);
        Before1PMList.add(3);

        Date dateCurrent = Calendar.getInstance().getTime();
        int currentHour = Calendar.getInstance().get(Calendar.HOUR);
        Calendar now = Calendar.getInstance();
        int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);//Current hour
        mIntent = getIntent();
        IntentGetCartList = mIntent.getCharSequenceArrayListExtra("CartList");
        int RestNO = mIntent.getIntExtra("RestNO", 0);
        intValue = mIntent.getIntExtra("OrderNumber", 0);
        ID = mIntent.getIntExtra("OrderID", 0);
        Status = mIntent.getStringExtra("OrderStatus");
        SubTotalPrice = mIntent.getStringExtra("OrderSubTotal");
        db = FirebaseDatabase.getInstance();
        reference = db.getReference(FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Orders/Order" + intValue);
        dbTrack = FirebaseDatabase.getInstance();
        referenceTrack = dbTrack.getReference("TrackOrder");
        getSupportActionBar().setElevation(0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_your_order);
        RadioButton1 = findViewById(R.id.radioButton1);
        RadioButton2 = findViewById(R.id.radioButton2);
        RadioButton3 = findViewById(R.id.radioButton3);
        RadioButton4 = findViewById(R.id.radioButton4);

        RadioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton4.setError(null);
                RadioButton1.setChecked(true);
                RadioButton3.setError(null);
                SelectedGate = "3";
                RadioButton2.setChecked(false);

            }
        });
        RadioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton2.setChecked(true);
                RadioButton4.setError(null);
                RadioButton3.setError(null);
                SelectedGate = "4";
                RadioButton1.setChecked(false);

            }
        });

        RadioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton3.setChecked(true);
                RadioButton4.setChecked(false);
                SelectedTime = "12:00 noon";
//                PayBtn.setEnabled(true);
                if (!Before10AMList.contains(currentHour)) {
                    if (now.get(Calendar.AM_PM) == Calendar.AM) {
                        // AM
                        Toast.makeText(PlaceYourOrder_Activity.this, "You have to place your order before 10:00 AM", Toast.LENGTH_SHORT).show();
                    } else {
                        // PM
                        Toast.makeText(PlaceYourOrder_Activity.this, "You have to place your order before 10:00 AM", Toast.LENGTH_SHORT).show();
                    }

                }

             else {
                   if (now.get(Calendar.AM_PM) == Calendar.AM) {

                   }
                    else
                 {
                     Toast.makeText(PlaceYourOrder_Activity.this, "You have to place your order before 10:00 AM", Toast.LENGTH_SHORT).show();
                   }
             }

           }
        });
        RadioButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RadioButton4.setChecked(true);
                RadioButton3.setChecked(false);
                SelectedTime = "3:00 PM";

                if (!Before1PMList.contains(currentHour))
                {
                    Toast.makeText(PlaceYourOrder_Activity.this, "You have to place your order before 1:00 PM",Toast.LENGTH_SHORT).show();
                }

                else {
                    if (currentHour == 12) {
                        if (now.get(Calendar.AM_PM) == Calendar.PM) {

                        } else {
                            Toast.makeText(PlaceYourOrder_Activity.this, "You have to place your order before 1:00 PM",Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        if (now.get(Calendar.AM_PM) == Calendar.AM) {

                        } else {
                            Toast.makeText(PlaceYourOrder_Activity.this, "You have to place your order before 1:00 PM",Toast.LENGTH_SHORT).show();

                        }

                    }
                }
            }
        });
        CardNumber = findViewById(R.id.CardNumber);
        PayBtn = findViewById(R.id.PayBtn);
        PayBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                values.put("OrderID", ID);
                values.put("OrderGate", SelectedGate);
                values.put("OrderTime", SelectedTime);
                values.put("OrderStatus", Status);
                values.put("OrderSubTotal", SubTotalPrice);
                values.put("RestNO", RestNO);
                reference.child("OrderDetails").updateChildren(values);

                valuesOfTrack.put("OrderID", ID);
                valuesOfTrack.put("UserUID", FirebaseAuth.getInstance().getCurrentUser().getUid());
                referenceTrack.child("Orders").updateChildren(valuesOfTrack);

                intent = new Intent(PlaceYourOrder_Activity.this, CheckoutActivity.class);
                startActivity(intent);

            }
        });
    }
}