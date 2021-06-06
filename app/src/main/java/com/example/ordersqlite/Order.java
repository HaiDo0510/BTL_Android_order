package com.example.ordersqlite;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private String name;
    private String date;
    private double price;
    private String address;
    private String status;

    public Order() {
    }

    public Order(int id, String name, String date, double price, String address, String status) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.price = price;
        this.address = address;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
