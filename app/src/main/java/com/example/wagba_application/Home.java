package com.example.wagba_application;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Restaurant> RestaurantList;
    private  String[] RestaurantTitle;
    private  int[] RestaurantImage;
    private RecyclerView recyclerView;
    private String[] RestaurantDescription;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("Restaurants");


    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RestaurantList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        MyAdapter myAdapter = new MyAdapter(getContext(), RestaurantList);
        recyclerView.setAdapter(myAdapter);


//        myAdapter.notifyDataSetChanged();

       ref.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               RestaurantList.clear();
               for ( DataSnapshot dataSnapshot : snapshot.getChildren())
               {
                   Restaurant restaurant = dataSnapshot.getValue(Restaurant.class);
                   RestaurantList.add(restaurant);
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
//        RestaurantList = new ArrayList<>();
//        RestaurantTitle = new String[]{
//                getString(R.string.item1),
//                getString(R.string.item1),
//                getString(R.string.item1),
//                getString(R.string.item1),
//                getString(R.string.item1),
//                getString(R.string.item1),
//                getString(R.string.item1),
//                getString(R.string.item1),
//                getString(R.string.item1),
//        };
//
//        RestaurantDescription = new String[]{
//                getString(R.string.burgerkingdescription),
//                getString(R.string.burgerkingdescription),
//                getString(R.string.burgerkingdescription),
//                getString(R.string.burgerkingdescription),
//                getString(R.string.burgerkingdescription),
//                getString(R.string.burgerkingdescription),
//                getString(R.string.burgerkingdescription),
//                getString(R.string.burgerkingdescription),
//                getString(R.string.burgerkingdescription),
//        };
//
//
//        RestaurantImage = new int[]
//                {
//                        R.drawable.img_7,
//                        R.drawable.img_7,
//                        R.drawable.img_7,
//                        R.drawable.img_7,
//                        R.drawable.img_7,
//                        R.drawable.img_7,
//                        R.drawable.img_7,
//                        R.drawable.img_7,
//                        R.drawable.img_7,
//                };
//
//        for (int i =0; i<RestaurantTitle.length; i++)
//        {
//            Restaurant restaurant = new Restaurant( RestaurantImage[i],RestaurantTitle[i], RestaurantDescription[i]);
//            RestaurantList.add(restaurant);
//
//        }
//    }


    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant, container, false);
    }
}