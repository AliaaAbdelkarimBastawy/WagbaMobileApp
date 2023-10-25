package com.example.wagba_application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class plateActivity extends AppCompatActivity {
    Intent i = getIntent();
    private  String[] PlateTitle;
    private  int[] PlateImage;
    private RecyclerView recyclerView;
    private String[] PlatePrice;
    private String[] PlateNoOfItems;
    Button AddToCartButton;
    Intent intent;
    Intent mIntent;
    int intValue;
    ArrayList<Plate> PlateList = new ArrayList<>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       mIntent = getIntent();
        intValue = mIntent.getIntExtra("WhichRestau", 0);
        if (intValue == 0)
        {
            ref = database.getReference(
                    "Restaurants").child("Restaurant1/Menu");
        }

        else if (intValue ==1)
        {
            ref = database.getReference(
                    "Restaurants").child("Restaurant10/Menu");
        }

        else if (intValue ==2)
        {
            ref = database.getReference(
                    "Restaurants").child("Restaurant2/Menu");
        }

        else if (intValue ==3)
        {
            ref = database.getReference(
                    "Restaurants").child("Restaurant3/Menu");
        }
        else if (intValue ==4)
        {
            ref = database.getReference(
                    "Restaurants").child("Restaurant4/Menu");
        }
        else if (intValue ==5)
        {
            ref = database.getReference(
                    "Restaurants").child("Restaurant5/Menu");
        }

        else if (intValue ==6)
        {
            ref = database.getReference(
                    "Restaurants").child("Restaurant6/Menu");
        }
        else if (intValue ==7)
        {
            ref = database.getReference(
                    "Restaurants").child("Restaurant7/Menu");
        }

        else if (intValue ==8)
        {
            ref = database.getReference(
                    "Restaurants").child("Restaurant8/Menu");
        }
        else if (intValue ==9)
        {
            ref = database.getReference(
                    "Restaurants").child("Restaurant9/Menu");
        }

        getSupportActionBar().setElevation(0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate);
        AddToCartButton = findViewById(R.id.AddToCartButton);
        AddToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(plateActivity.this, CartActivity.class);
                intent.putExtra("WhichRestau",intValue);
                startActivity(intent);
            }
        });

//        dataInitialize();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        System.out.println("CREATE ADAPTER");
        PlateAdapter myAdapter = new PlateAdapter(this, PlateList, intValue);
        System.out.println("SET ADAPTER");

        recyclerView.setAdapter(myAdapter);


//        myAdapter.notifyDataSetChanged();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PlateList.clear();
                for ( DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Plate plate = dataSnapshot.getValue(Plate.class);
                    System.out.println("ADD TO LIST");

                    PlateList.add(plate);
                }

                System.out.println("notify data changed");

                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}