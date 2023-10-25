package com.example.wagba_application;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
ArrayList<Restaurant> restaurantsArrayList;
    FirebaseDatabase db;
    DatabaseReference reference ;
    public MyAdapter(Context context, ArrayList<Restaurant>restaurantsArrayList)
    {
        this.context = context;
        this.restaurantsArrayList = restaurantsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Restaurant restaurants = restaurantsArrayList.get(position);
        holder.title.setText(restaurants.getName());
        Glide.with(context)
                .load(restaurants.getImage())
                .into(holder.image);
        holder.description.setText(restaurants.getDescription());
        holder.off.setText(restaurants.getOff());
        holder.rate.setText(restaurants.getRate());
        holder.distance.setText(restaurants.getDistance());
    }

    @Override
    public int getItemCount() {
        return restaurantsArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
//        ShapeableImageView image;
        TextView title;
        TextView description;
        TextView rate;
        TextView off;
        TextView distance;
        ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rate = itemView.findViewById(R.id.rate);
            off = itemView.findViewById(R.id.off);
            distance = itemView.findViewById(R.id.distance);
            title = itemView.findViewById(R.id.title_Plate);
            description = itemView.findViewById(R.id.tvHeading2);
            image = itemView.findViewById(R.id.ImageRestaurant);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),
                            plateActivity.class);
                   int item = getAdapterPosition();
                    intent.putExtra("WhichRestau",item);
                    System.out.println(item);
                    v.getContext().startActivity(intent);
                }

            }); }


        }
    }

