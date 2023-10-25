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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;


public class PlateAdapter  extends RecyclerView.Adapter<PlateAdapter.MyViewHolder> {
    Context context;
    int WhichRestaurant;
    ArrayList<Plate> plateArrayList;
    static DatabaseReference databaseReference;

    public PlateAdapter(Context context, ArrayList<Plate> plateArrayList, int WhichRestaurant) {
        this.context = context;
        this.plateArrayList = plateArrayList;
        this.WhichRestaurant = WhichRestaurant;

    }

    @NonNull
    @Override
    public PlateAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.order_plate_item, parent, false);

        return new PlateAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlateAdapter.MyViewHolder holder, int position) {
        Plate plate = plateArrayList.get(position);
        holder.title.setText(plate.getName());
        Glide.with(context)
                .load(plate.getImage())
                .into(holder.image);
        holder.price.setText(plate.getPrice());
        holder.NoItems.setText(String.valueOf(plate.getNoOfItems()));
        holder.Availability.setText(plate.getAvailability());

    }

    @Override
    public int getItemCount() {
        return plateArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;
        TextView price;
        TextView NoItems;
        TextView Availability;
        ImageView AddBtn;
        ImageView RemoveBtn;
        int value;
        private String WhichDish;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.PlateImage);
            title = itemView.findViewById(R.id.title_Plate);
            price = itemView.findViewById(R.id.off);
            NoItems = itemView.findViewById(R.id.No_Of_Items_Plate);
            Availability = itemView.findViewById(R.id.Availability);
            AddBtn = itemView.findViewById(R.id.PlusBtn);
            RemoveBtn = itemView.findViewById(R.id.MinusBtn);
            AddBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    value = getAdapterPosition();
                    System.out.println(value);
                    String text = (String) NoItems.getText();

                    int number = Integer.parseInt(text);
                    number ++;
                    updatedata(number, value);
                }
            });

            RemoveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    value = getAdapterPosition();
                    System.out.println(value);
                    String text = (String) NoItems.getText();
                    int number = Integer.parseInt(text);
                    number --;
                    if (number <= 0)
                    {
                        number = 0;
                    }
                    updatedata(number, value);
//                    NoItems.setText(String.valueOf(number));
                }
            });
        }

//        String WhichDishePath = "Restaurant1/Menu/Dish1";
        private void updatedata(int number, int value) {

            HashMap UpdateNoOfItems = new HashMap();
            UpdateNoOfItems.put("NoOfItems",number);
            databaseReference = FirebaseDatabase.getInstance().getReference("Restaurants");

            if (WhichRestaurant == 0 & value == 0)
            {
                databaseReference.child("Restaurant1/Menu/Dish1").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 0 & value == 1)
            {
                databaseReference.child("Restaurant1/Menu/Dish2").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 0 & value == 2)
            {
                databaseReference.child("Restaurant1/Menu/Dish3").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 0 & value == 3)
            {
                databaseReference.child("Restaurant1/Menu/Dish4").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 1 & value == 0)
            {
                databaseReference.child("Restaurant10/Menu/Dish1").
                        updateChildren(UpdateNoOfItems);
            }
            else if (WhichRestaurant == 1 & value == 1)
            {
                databaseReference.child("Restaurant10/Menu/Dish2").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 1 & value == 2)
            {
                databaseReference.child("Restaurant10/Menu/Dish3").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 1 & value == 3)
            {
                databaseReference.child("Restaurant10/Menu/Dish4").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 2 & value == 0)
            {
                databaseReference.child("Restaurant2/Menu/Dish1").
                        updateChildren(UpdateNoOfItems);
            }
            else if (WhichRestaurant == 2 & value == 1)
            {
                databaseReference.child("Restaurant2/Menu/Dish2").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 2 & value == 2)
            {
                databaseReference.child("Restaurant2/Menu/Dish3").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 2 & value == 3)
            {
                databaseReference.child("Restaurant2/Menu/Dish4").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 3 & value == 0)
            {
                databaseReference.child("Restaurant3/Menu/Dish1").
                        updateChildren(UpdateNoOfItems);
            }
            else if (WhichRestaurant == 3 & value == 1)
            {
                databaseReference.child("Restaurant3/Menu/Dish2").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 3 & value == 2)
            {
                databaseReference.child("Restaurant3/Menu/Dish3").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 3 & value == 3)
            {
                databaseReference.child("Restaurant3/Menu/Dish4").
                        updateChildren(UpdateNoOfItems);
            }


            else if (WhichRestaurant == 4 & value == 0)
            {
                databaseReference.child("Restaurant4/Menu/Dish1").
                        updateChildren(UpdateNoOfItems);
            }
            else if (WhichRestaurant == 4 & value == 1)
            {
                databaseReference.child("Restaurant4/Menu/Dish2").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 4 & value == 2)
            {
                databaseReference.child("Restaurant4/Menu/Dish3").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 4 & value == 3)
            {
                databaseReference.child("Restaurant4/Menu/Dish4").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 5 & value == 0)
            {
                databaseReference.child("Restaurant5/Menu/Dish1").
                        updateChildren(UpdateNoOfItems);
            }
            else if (WhichRestaurant == 5 & value == 1)
            {
                databaseReference.child("Restaurant5/Menu/Dish2").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 5 & value == 2)
            {
                databaseReference.child("Restaurant5/Menu/Dish3").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 5 & value == 3)
            {
                databaseReference.child("Restaurant5/Menu/Dish4").
                        updateChildren(UpdateNoOfItems);
            }


            else if (WhichRestaurant == 6 & value == 0)
            {
                databaseReference.child("Restaurant6/Menu/Dish1").
                        updateChildren(UpdateNoOfItems);
            }
            else if (WhichRestaurant == 6 & value == 1)
            {
                databaseReference.child("Restaurant6/Menu/Dish2").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 6 & value == 2)
            {
                databaseReference.child("Restaurant6/Menu/Dish3").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 6 & value == 3)
            {
                databaseReference.child("Restaurant6/Menu/Dish4").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 7 & value == 0)
            {
                databaseReference.child("Restaurant7/Menu/Dish1").
                        updateChildren(UpdateNoOfItems);
            }
            else if (WhichRestaurant == 7 & value == 1)
            {
                databaseReference.child("Restaurant7/Menu/Dish2").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 7 & value == 2)
            {
                databaseReference.child("Restaurant7/Menu/Dish3").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 7 & value == 3)
            {
                databaseReference.child("Restaurant7/Menu/Dish4").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 8 & value == 0)
            {
                databaseReference.child("Restaurant8/Menu/Dish1").
                        updateChildren(UpdateNoOfItems);
            }
            else if (WhichRestaurant == 8 & value == 1)
            {
                databaseReference.child("Restaurant8/Menu/Dish2").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 8 & value == 2)
            {
                databaseReference.child("Restaurant8/Menu/Dish3").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 8 & value == 3)
            {
                databaseReference.child("Restaurant8/Menu/Dish4").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 9 & value == 0)
            {
                databaseReference.child("Restaurant9/Menu/Dish1").
                        updateChildren(UpdateNoOfItems);
            }
            else if (WhichRestaurant == 9 & value == 1)
            {
                databaseReference.child("Restaurant9/Menu/Dish2").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 9 & value == 2)
            {
                databaseReference.child("Restaurant9/Menu/Dish3").
                        updateChildren(UpdateNoOfItems);
            }

            else if (WhichRestaurant == 9 & value == 3)
            {
                databaseReference.child("Restaurant9/Menu/Dish4").
                        updateChildren(UpdateNoOfItems);
            }


        }


        }

    }


