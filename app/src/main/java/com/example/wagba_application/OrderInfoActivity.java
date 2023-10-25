package com.example.wagba_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderInfoActivity extends AppCompatActivity {
    public static int intValue;
    private  String[] CartTitle;
    private  int[] CartImage;
    private RecyclerView recyclerView;
    private String[] CartPrice;
    private String[] CartNoOfItems;
    Intent mIntent;
    String StringValue;
    TextView Gate;
    TextView Time;
    String gate;
    String time;
    double SubTotal = 0 ;
    Double DeliveryFees =15.0;
    int RestaurantNumber;
    double Total = 0;
    Button AddToCart_ContinueBtn;
    String NameOfRestaurant;
    TextView NoOfTotalItems_Textview;
    int NoOfTotalItems;
    CartAdapter myAdapter;
    ArrayList<Cart> CartList0 = new ArrayList<>();
    ArrayList<Cart> CartList1 = new ArrayList<>();
    ArrayList<Cart> CartList2 = new ArrayList<>();
    ArrayList<Cart> CartList3 = new ArrayList<>();
    ArrayList<Cart> CartList4 = new ArrayList<>();
    ArrayList<Cart> CartList5 = new ArrayList<>();
    ArrayList<Cart> CartList6 = new ArrayList<>();
    ArrayList<Cart> CartList7 = new ArrayList<>();
    ArrayList<Cart> CartList8 = new ArrayList<>();
    ArrayList<Cart> CartList9 = new ArrayList<>();

    ArrayList<Cart> CartList= new ArrayList<>();

    TextView IdOfOrder;
    TextView StatusOfOrder;
    TextView SubTotalPriceOfOrder;
    TextView TotalValue;
    TextView Delivery;
    TextView SubTotalvalue;
    TextView Restaurant_Name;
    public static int CheckListFullness = 0;
    DatabaseReference reference_4;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref ;
    ArrayList<OrderInfo> OrderInfoList = new ArrayList<OrderInfo>();
    ArrayList<Cart> OrderInfoListt = new ArrayList<Cart>();
    ArrayList<Integer> WhichRestList = new ArrayList<Integer>();
    FirebaseDatabase db_4 = FirebaseDatabase.getInstance();
    FirebaseDatabase db_5 = FirebaseDatabase.getInstance();
    DatabaseReference reference_5;
    FirebaseDatabase db_6 = FirebaseDatabase.getInstance();
    DatabaseReference reference_6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        IdOfOrder = findViewById(R.id.OrderID);
        StatusOfOrder = findViewById(R.id.OrderStatus);
        SubTotalPriceOfOrder = findViewById(R.id.SubTotalPRICE);
        Gate = findViewById(R.id.OrderGate);
        Time = findViewById(R.id.OrderTime);
        mIntent = getIntent();
        IdOfOrder.setText("OKKKKK");
        StringValue = mIntent.getStringExtra("WhichOrderHistory");
        reference_5 = db_5.getReference(FirebaseAuth.getInstance().getCurrentUser().getUid()+"/Orders");
        reference_5.child(StringValue).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot dataSnapshot = task.getResult();
                String j = String.valueOf(dataSnapshot
                        .child("OrderDetails").child("RestNO").getValue());
                if (j != null)
                {
                    OrderInfoActivity.intValue = Integer.parseInt(j);
//                    CartList0.clear();

                }
                System.out.println("STRING VALUE ORDER NUMBER" +j);

            }
        });
     ref = database.getReference(FirebaseAuth
             .getInstance().getCurrentUser().getUid()+"/Cart/" + StringValue+"/Dishes");

        reference_6 = db_6.getReference(FirebaseAuth.getInstance().getCurrentUser().getUid()+"/Orders");
        reference_6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                reference_6.child(StringValue+"/OrderDetails").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful())
                        {
                            if (task.getResult().exists())
                            {
                                DataSnapshot dataSnapshot = task.getResult();
                                String OrderId = String.valueOf(dataSnapshot.child("OrderID").getValue());
                                String OrderStatus = String.valueOf(dataSnapshot.child("OrderStatus").getValue());
                                String OrderSubTotal = String.valueOf(dataSnapshot.child("OrderSubTotal").getValue());
                                String OrderGate = String.valueOf(dataSnapshot.child("OrderGate").getValue());
                                String OrderTime = String.valueOf(dataSnapshot.child("OrderTime").getValue());

                                IdOfOrder.setText(OrderId);
                                StatusOfOrder.setText(OrderStatus);
                                SubTotalPriceOfOrder.setText(OrderSubTotal);
                                Gate.setText(OrderGate);
                                Time.setText(OrderTime);

                            }
                        }

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //        dataInitialize();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


//        myAdapter.notifyDataSetChanged();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                CartList0.clear();
                NoOfTotalItems = 0;
                Total = 0;
                for ( DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Cart cart = dataSnapshot.getValue(Cart.class);
                        CartList0.add(cart);
                }
                CartAdapter myAdapter = new CartAdapter(OrderInfoActivity.this, CartList0);
                recyclerView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


//    private  void  dataInitialize()
//    {
//        CartList = new ArrayList<>();
//        CartTitle = new String[]{
//                getString(R.string.PlateTitle),
//                getString(R.string.PlateTitle),
//                getString(R.string.PlateTitle),
//                getString(R.string.PlateTitle),
//                getString(R.string.PlateTitle),
//                getString(R.string.PlateTitle),
//                getString(R.string.PlateTitle),
//                getString(R.string.PlateTitle),
//                getString(R.string.PlateTitle),
//
//        };
//
//        CartPrice = new String[]{
//                getString(R.string.NetPrice),
//                getString(R.string.NetPrice),
//                getString(R.string.NetPrice),
//                getString(R.string.NetPrice),
//                getString(R.string.NetPrice),
//                getString(R.string.NetPrice),
//                getString(R.string.NetPrice),
//                getString(R.string.NetPrice),
//                getString(R.string.NetPrice),
//        };
//
//
//        CartImage = new int[]
//                {
//                        R.drawable.img_6,
//                        R.drawable.img_6,
//                        R.drawable.img_6,
//                        R.drawable.img_6,
//                        R.drawable.img_6,
//                        R.drawable.img_6,
//                        R.drawable.img_6,
//                        R.drawable.img_6,
//                        R.drawable.img_6,
//
//                };
//
//        CartNoOfItems = new String[]
//                {
//                        getString(R.string.PlateNo_Of_items),
//                        getString(R.string.PlateNo_Of_items),
//                        getString(R.string.PlateNo_Of_items),
//                        getString(R.string.PlateNo_Of_items),
//                        getString(R.string.PlateNo_Of_items),
//                        getString(R.string.PlateNo_Of_items),
//                        getString(R.string.PlateNo_Of_items),
//                        getString(R.string.PlateNo_Of_items),
//                        getString(R.string.PlateNo_Of_items),
//                };
//        for (int i =0; i<CartTitle.length; i++)
//        {
//            Cart cart = new Cart( CartImage[i],CartTitle[i], CartPrice[i], CartNoOfItems[i]);
//            CartList.add(cart);
//
//        }
//    }
}
