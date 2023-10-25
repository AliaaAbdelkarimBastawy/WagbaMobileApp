package com.example.wagba_application;

public class OrderHistory {

    String OrderName;
    String Image;
    String NoOfItems;

    public OrderHistory() {
    }

    public OrderHistory(String orderName, String image, String noOfItems) {
        OrderName = orderName;
        Image = image;
        NoOfItems = noOfItems;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public void setOrderName(String orderName) {
        OrderName = orderName;
    }



    public void setNoOfItems(String noOfItems) {
        NoOfItems = noOfItems;
    }

    public String getNoOfItems() {
        return NoOfItems;
    }

//    public String getImage() {
//        return Image;
//    }

    public String getOrderName() {
        return OrderName;
    }
}
