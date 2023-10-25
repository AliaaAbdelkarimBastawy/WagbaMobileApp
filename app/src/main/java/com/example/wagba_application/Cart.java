package com.example.wagba_application;

import java.io.Serializable;

public class Cart implements Serializable {
    String Name;
    String Image;
    String Price;
    int NoOfItems;
    int NetPrice;


    public int getNetPrice() {
        return NetPrice;
    }

    public String getImage() {
        return Image;
    }

    public int getNoOfItems() {
        return NoOfItems;
    }

    public String getPrice() {
        return Price;
    }

    public String getName() {
        return Name;
    }

}
