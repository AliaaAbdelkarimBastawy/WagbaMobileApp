package com.example.wagba_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {
    double SubTotal = 0 ;
    Double DeliveryFees =15.0;
    int RestaurantNumber;
    double Total = 0;
    Button AddToCart_ContinueBtn;
    String NameOfRestaurant;
    TextView NoOfTotalItems_Textview;
    TextView TotalValue;
    TextView Delivery;
    TextView SubTotalvalue;
    TextView Restaurant_Name;
    private  String[] CartTitle;
    private  int[] CartImage;
    private RecyclerView recyclerView;
    HashMap<String, Object> values = new HashMap<>();
    private String[] CartPrice;
    private String[] CartNoOfItems;
    Intent intent;
    Intent mIntent;
    int intValue;
    public static int counter = 1;
    public static int RequestedDish = 1;

    public static final String PREFS_NAME = "MyPreferenceFile";
    ArrayList<Integer> CounterList = new ArrayList<>();
    int NoOfTotalItems;
    FirebaseDatabase db_3;
    DatabaseReference reference_3;
    static final ArrayList<Cart> CartList = new ArrayList<>();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref ;
    ArrayList<Restaurant> RestaurantList = new ArrayList<>();

    FirebaseDatabase db;
    DatabaseReference reference ;

    FirebaseDatabase db_2 = FirebaseDatabase.getInstance();

    DatabaseReference reference_2;
    FirebaseDatabase db_6= FirebaseDatabase.getInstance();

    DatabaseReference reference_6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = FirebaseDatabase.getInstance();
        reference = db.getReference(FirebaseAuth.getInstance().getCurrentUser().getUid()+"/Orders");

        reference_2 = db_2.getReference("Restaurants");
        db_3 = FirebaseDatabase.getInstance();
        reference_3 = db_3.getReference("Orders").child("Order"+ intValue);
        super.onCreate(savedInstanceState);
        mIntent = getIntent();
        intValue = mIntent.getIntExtra("WhichRestau", 0);
        int RestNO =  mIntent.getIntExtra("WhichRestau", 0);
        if (intValue == 0)
        {
            ref = database.getReference(
                    "Restaurants").child("Restaurant1/Menu");

            NameOfRestaurant = "Food Corner";
            RestaurantNumber = 0;
        }

        else if (intValue ==1)
        {
            ref = database.getReference(
                    "Restaurants").child("Restaurant10/Menu");
            NameOfRestaurant = "Nagaf";
            RestaurantNumber = 1;

        }

        else if (intValue ==2)
        {
            ref = database.getReference(
                    "Restaurants").child("Restaurant2/Menu");

            NameOfRestaurant = "Tayebat El Sham";
            RestaurantNumber = 2;

        }

        else if (intValue ==3)
        {
            ref = database.getReference(
                    "Restaurants").child("Restaurant3/Menu");

            NameOfRestaurant = "City Crepe";
            RestaurantNumber = 3;
        }
        else if (intValue ==4)
        {
            ref = database.getReference(
                    "Restaurants").child("Restaurant4/Menu");
            NameOfRestaurant = "KFC";
            RestaurantNumber = 4;

        }
        else if (intValue ==5)
        {
            ref = database.getReference(
                    "Restaurants").child("Restaurant5/Menu");
            NameOfRestaurant = "El Shabrawy";
            RestaurantNumber = 5;
        }

        else if (intValue ==6)
        {
            ref = database.getReference(
                    "Restaurants").child("Restaurant6/Menu");
            NameOfRestaurant = "Buffalo Burger";
            RestaurantNumber = 6;

        }
        else if (intValue ==7)
        {
            ref = database.getReference(
                    "Restaurants").child("Restaurant7/Menu");
            NameOfRestaurant = "Om Hassan";
            RestaurantNumber = 7;

        }

        else if (intValue ==8)
        {
            ref = database.getReference(
                    "Restaurants").child("Restaurant8/Menu");
            NameOfRestaurant = "Pizza Hut";
            RestaurantNumber = 8;

        }
        else if (intValue ==9)
        {
            ref = database.getReference(
                    "Restaurants").child("Restaurant9/Menu");
            NameOfRestaurant = "Prego";
            RestaurantNumber = 9;

        }

        setContentView(R.layout.activity_cart);
        getSupportActionBar().setElevation(0);
        Restaurant_Name = findViewById(R.id.Restaurant_Name);
        NoOfTotalItems_Textview = findViewById(R.id.NoOfTotalItems);
        TotalValue = findViewById(R.id.TotalValue);
        SubTotalvalue = findViewById(R.id.SubTotalValue);
        Delivery = findViewById(R.id.DeliveryValue);

        AddToCart_ContinueBtn = findViewById(R.id.AddToCart_ContinueBtn);
        reference_2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Restaurant restaurant = dataSnapshot.getValue(Restaurant.class);
                    RestaurantList.add(restaurant);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        int i=1;
        AddToCart_ContinueBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String ItemsNo = NoOfTotalItems + "items";

//
//                values.put("OrderID", 1);
//                values.put("OrderStatus", "Delivered");
//                values.put("Subtotal", "150 EGP");
//                reference_3.child("OrderDetails").setValue(values);

//                reference.child("OrderDetails").setValue(values);
//                reference_3.child("OrderDetails").updateChildren(values);
//
//                MainOrderDetails mainOrderDetails =
//                        new MainOrderDetails(1,
//                                "Delivered",
//                                "150 EGP");
//                reference_3.child("OrderDetails").setValue(mainOrderDetails);

//                reference_3.updateChildren(mainOrderDetails);
                while (CounterList.contains( CartActivity.counter) == true)
                {
                    CartActivity.counter++;
                }

                System.out.println("CURREEENNTTT USERRR " + FirebaseAuth.getInstance().getCurrentUser());
                CounterList.add(CartActivity.counter);
                OrderHistory orderHistory =
                        new OrderHistory("Order"+ counter,
                                RestaurantList.get(intValue).Image,ItemsNo);
                reference.child("Order"+ counter).setValue(orderHistory);
                System.out.println("Counter =" + intValue);
                intent = new Intent(CartActivity.this, PlaceYourOrder_Activity.class);
                intent.putExtra("OrderNumber",counter);
                intent.putExtra("OrderID", counter);
                intent.putExtra("RestNO", intValue);
                intent.putExtra("OrderStatus", "");
                intent.putExtra("OrderSubTotal", SubTotal +"    " +"EGP");
//                intValue = intValue+ 1;
                CartActivity.counter++;
                startActivity(intent);

            }
        });

//        dataInitialize();



        reference_6 = db_6.getReference(FirebaseAuth.getInstance()
                .getCurrentUser().getUid()+"/Cart/" + "Order"+ counter);
//        dataInitialize();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        CartAdapter myAdapter = new CartAdapter(this, CartList);

        recyclerView.setAdapter(myAdapter);
        Delivery.setText(String.valueOf(DeliveryFees));
        Restaurant_Name.setText(NameOfRestaurant);
//        myAdapter.notifyDataSetChanged();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                CartList.clear();
                NoOfTotalItems = 0;
                Total = 0;
                for ( DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Cart cart = dataSnapshot.getValue(Cart.class);
                    if(cart.NoOfItems > 0)
                    {
                        CartList.add(cart);
                        values.put("NetPrice", cart.NetPrice);
                        values.put("NoOfItems", cart.NoOfItems);
                        values.put("Name", cart.Name);
                        values.put("Image", cart.Image);
//                        values.put("RestNO", RestNO);
                        reference_6.child("Dishes/RequestedDish"+RequestedDish).updateChildren(values);
                        RequestedDish ++;
//                        counter ++;
                        System.out.println("CART" +CartList);
                        NoOfTotalItems = NoOfTotalItems+ cart.getNoOfItems();
                        NoOfTotalItems_Textview.setText(String.valueOf(NoOfTotalItems));
                        Total = Total + (cart.getNetPrice() * cart.getNoOfItems());
                        TotalValue.setText(String.valueOf(Total));
                        SubTotal = Total + DeliveryFees;
                        SubTotalvalue.setText(String.valueOf(SubTotal) + " " +"EGP")

                        ;
                    }
//                    CartList.add(cart);
                }

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
