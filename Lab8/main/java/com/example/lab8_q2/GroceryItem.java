package com.example.lab8_q2;

public class GroceryItem {
    private int id;
    private String category;
    private String name;
    private double price;

    public GroceryItem(int id, String category, String name, double price) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public int getId() { return id; }
    public String getCategory() { return category; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}

