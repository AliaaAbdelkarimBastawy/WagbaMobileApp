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

import java.util.ArrayList;

public class OrderInfoAdapter  extends RecyclerView.Adapter<OrderInfoAdapter.MyViewHolder>{
    Context context;
    ArrayList<Cart> OrderHistoryArrayList;
    public OrderInfoAdapter(Context context, ArrayList<Cart> CartArrayList)
    {
        this.context = context;
        this.OrderHistoryArrayList = CartArrayList;
    }

    @NonNull
    @Override
    public OrderInfoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);

        return new OrderInfoAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderInfoAdapter.MyViewHolder holder, int position) {
        Cart cart = OrderHistoryArrayList.get(position);
        holder.title.setText(cart.getName());
        Glide.with(context)
                .load(cart.getImage())
                .into(holder.image);
        holder.price.setText(String.valueOf(cart.getNetPrice()));
        holder.NoItems.setText(String.valueOf(cart.getNoOfItems()));

    }

    @Override
    public int getItemCount() {

        return OrderHistoryArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;
        TextView price;
        TextView NoItems;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_Plate2);
            title = itemView.findViewById(R.id.title_Plate2);
            price = itemView.findViewById(R.id.price_Plate2);
            NoItems = itemView.findViewById(R.id.No_Of_Items_Plate2);

        }
    }
}


