package com.echoece.favlistapp;

import java.util.ArrayList;

/* Class created to hold the values of the category that are created dynamically. */
public class Category {
    private String name;
    private ArrayList<String> items=new ArrayList<>();

    public Category(String name, ArrayList<String> items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getItems() {
        return items;
    }
}
