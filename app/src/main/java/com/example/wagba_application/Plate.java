package com.example.wagba_application;

import java.io.Serializable;

public class Plate implements Serializable {
    String Name;
    String Image;
    String Price;
    int NoOfItems;
    String Availability;

    public String getAvailability() {
        return Availability;
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
