package com.example.wagba_application;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link trackorder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class trackorder extends Fragment {
    public static String IDofOrder;
//    public static String StatusofOrder;
    Button button2;
    static int PERMISSION_CODE= 100;
    Intent intent;
    View OrderConfirm1;
    View OrderConfirm2;
    View OrderProcess1;
    View OrderProcess2;
    View OrderOnDelivery;
    TextView TextofStatus;
    TextView ConfirmStatus;
    TextView onDeliveryStatus;
    TextView inProcessingStatus;
    int j;
    FirebaseDatabase dbTrack;
    DatabaseReference referenceTrack ;
    int CurrentStatus;
    FirebaseDatabase dbStatus;
    DatabaseReference referenceStatus ;
    TextView ID;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String PhoneNumber;
    ImageView phone;
    String Confirmation = "Confirmed";
    String Delivery = "OnDelivery";
    String Processing = "in Processing";

    public trackorder() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment trackorder.
     */
    // TODO: Rename and change types and number of parameters
    public static trackorder newInstance(String param1, String param2) {
        trackorder fragment = new trackorder();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        PhoneNumber= "01237877221";

        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_trackorder, container, false);

        if (ContextCompat.checkSelfPermission(view.getContext(),Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},PERMISSION_CODE);

        }

        ID = view.findViewById(R.id.OrderNumber);
        dbTrack = FirebaseDatabase.getInstance();
        referenceTrack = dbTrack.getReference("TrackOrder");
        dbStatus = FirebaseDatabase.getInstance();
        referenceStatus = dbStatus.getReference(FirebaseAuth.getInstance().getCurrentUser().getUid()+"/Orders");
        TextofStatus = view.findViewById(R.id.StatusOrderinWhite);
        ConfirmStatus = (TextView) view.findViewById(R.id.ConfirmStatus);
        onDeliveryStatus = (TextView) view.findViewById(R.id.onDeliveryStatus);
        inProcessingStatus = (TextView) view.findViewById(R.id.inProcessingStatus);
        ConfirmStatus.setText("Confirmed");
        inProcessingStatus.setText("inProcessing");
        View circle = view.findViewById(R.id.OrderOnDelivery);
        OrderConfirm1 = view.findViewById(R.id.OrderConfirm1);
        OrderConfirm2 = view.findViewById(R.id.OrderConfirm2);
        OrderProcess1 = view.findViewById(R.id.OrderProcessed1);
        OrderProcess2 = view.findViewById(R.id.OrderProcessed2);
        OrderOnDelivery = view.findViewById(R.id.OrderOnDelivery);


        referenceTrack.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String OrderStatus;
                referenceTrack.child("Orders").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful())
                        {
                                DataSnapshot dataSnapshot = task.getResult();
                                trackorder.IDofOrder = String.valueOf(dataSnapshot.child("OrderID").getValue());
                                ID.setText(IDofOrder);
                        }
                                    }

                                });}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        System.out.println("IDDDDDD" + ID.getText());
        referenceStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                referenceStatus.child("Order"+ID.getText()+"/OrderDetails").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful())
                        {
                            if (task.getResult().exists())
                            {
                                DataSnapshot dataSnapshot = task.getResult();
                                String StatusofOrder = String.valueOf(dataSnapshot.child("OrderStatus").getValue());
                                System.out.println(StatusofOrder);
                                TextofStatus.setText(StatusofOrder);

                                OrderConfirm1.setBackground(ContextCompat.getDrawable(view.getContext(),  R.drawable.shape_status_current));
                                OrderConfirm2.setBackground(ContextCompat.getDrawable(view.getContext(),  R.drawable.shape_status_current));
                                OrderProcess1.setBackground(ContextCompat.getDrawable(view.getContext(),  R.drawable.shape_status_current));
                                OrderProcess2.setBackground(ContextCompat.getDrawable(view.getContext(),  R.drawable.shape_status_current));
                                OrderOnDelivery.setBackground(ContextCompat.getDrawable(view.getContext(),  R.drawable.shape_status_current));

                                if ((TextofStatus.getText().equals( "Delivered" ))) {
                                    System.out.println("The order is on Delivery");
                                    OrderConfirm1.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.shape_status_completed));
                                    OrderConfirm2.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.shape_status_completed));
                                    OrderProcess1.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.shape_status_completed));
                                    OrderProcess2.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.shape_status_completed));
                                    OrderOnDelivery.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.shape_status_completed));

                                }

                                else if (TextofStatus.getText().equals( "inProcessing"))
                                    {
                                        System.out.println("The order is on Processing");
                                        OrderConfirm1.setBackground(ContextCompat.getDrawable(view.getContext(),  R.drawable.shape_status_completed));
                                        OrderConfirm2.setBackground(ContextCompat.getDrawable(view.getContext(),  R.drawable.shape_status_completed));
                                        OrderProcess1.setBackground(ContextCompat.getDrawable(view.getContext(),  R.drawable.shape_status_completed));
                                        OrderProcess2.setBackground(ContextCompat.getDrawable(view.getContext(),  R.drawable.shape_status_current));
                                        OrderOnDelivery.setBackground(ContextCompat.getDrawable(view.getContext(),  R.drawable.shape_status_current));


                                    }

                                 else if (TextofStatus.getText().equals("Confirmed"))
                                {
                                    System.out.println("The order is Confirmed");
                                    OrderConfirm1.setBackground(ContextCompat.getDrawable(view.getContext(),  R.drawable.shape_status_completed));
                                    OrderConfirm2.setBackground(ContextCompat.getDrawable(view.getContext(),  R.drawable.shape_status_current));
                                    OrderProcess1.setBackground(ContextCompat.getDrawable(view.getContext(),  R.drawable.shape_status_current));
                                    OrderProcess2.setBackground(ContextCompat.getDrawable(view.getContext(),  R.drawable.shape_status_current));
                                    OrderOnDelivery.setBackground(ContextCompat.getDrawable(view.getContext(),  R.drawable.shape_status_current));

                                }

                                 else
                                {
                                    OrderConfirm1.setBackground(ContextCompat.getDrawable(view.getContext(),  R.drawable.shape_status_current));
                                    OrderConfirm2.setBackground(ContextCompat.getDrawable(view.getContext(),  R.drawable.shape_status_current));
                                    OrderProcess1.setBackground(ContextCompat.getDrawable(view.getContext(),  R.drawable.shape_status_current));
                                    OrderProcess2.setBackground(ContextCompat.getDrawable(view.getContext(),  R.drawable.shape_status_current));
                                    OrderOnDelivery.setBackground(ContextCompat.getDrawable(view.getContext(),  R.drawable.shape_status_current));
                                }
                            }


                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






        System.out.println("ONNNN DELIVERRYYYYY");

        phone = view.findViewById(R.id.phone);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+PhoneNumber));
                startActivity(i);
            }
        });


       return view;
    }
}