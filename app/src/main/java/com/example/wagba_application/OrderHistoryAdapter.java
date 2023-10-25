package com.example.wagba_application;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.MyViewHolder>{
    Context context;
    static String OrderName;
    static ArrayList<OrderHistory> orderArrayList;
    public OrderHistoryAdapter(Context context, ArrayList<OrderHistory>orderArrayList)
    {
        this.context = context;
        this.orderArrayList = orderArrayList;
    }

    @NonNull
    @Override
    public OrderHistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.order_history_item, parent, false);

        return new OrderHistoryAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryAdapter.MyViewHolder holder, int position) {
        OrderHistory orderHistory = orderArrayList.get(position);
        OrderName = orderHistory.getOrderName();
        holder.title.setText(orderHistory.getOrderName());
        Glide.with(context)
                .load(orderHistory.getImage())
                .into(holder.image);
        holder.NoItems.setText(orderHistory.getNoOfItems());
    }

    @Override
    public int getItemCount() {
        return orderArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ShapeableImageView image;
        TextView title;
        TextView NoItems;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.PlateImage);
            title = itemView.findViewById(R.id.title_Plate);
            NoItems = itemView.findViewById(R.id.off);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),
                            OrderInfoActivity.class);
                    int item = getAdapterPosition();
                    OrderName = orderArrayList.get(item).getOrderName();
                    System.out.println("ORDERNAMEEEE" +OrderName);
                    intent.putExtra("WhichOrderHistory",OrderName);
                    v.getContext().startActivity(intent);
                }}); }

    }
}