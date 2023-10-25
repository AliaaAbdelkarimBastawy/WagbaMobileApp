package com.example.wagba_application;

public class OrderInfo {
    String orderGate;
    String orderTime;


    public OrderInfo() {
    }

    public OrderInfo(String orderGate, String orderTime) {
        this.orderGate = orderGate;
        this.orderTime = orderTime;
    }

    public String getorderGate() {
        return orderGate;
    }

    public void setorderGate(String orderGate) {
        this.orderGate = orderGate;
    }

    public String getorderTime() {
        return orderTime;
    }

    public void setorderTime(String orderTime) {
        this.orderTime = orderTime;
    }

}
