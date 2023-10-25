package com.example.wagba_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>{
        Context context;
        ArrayList<Cart> CartArrayList;
public CartAdapter(Context context, ArrayList<Cart> CartArrayList)
        {
        this.context = context;
        this.CartArrayList = CartArrayList;
        }

@NonNull
@Override
public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);

        return new CartAdapter.MyViewHolder(v);
        }

@Override
public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, int position) {
    Cart cart = CartArrayList.get(position);
    holder.title.setText(cart.getName());
    Glide.with(context)
            .load(cart.getImage())
            .into(holder.image);
    holder.price.setText(String.valueOf(cart.getNetPrice()));
    System.out.println(holder.price.getText());
    holder.NoItems.setText(String.valueOf(cart.getNoOfItems()));

        }

@Override
public int getItemCount() {
        return CartArrayList.size();
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

