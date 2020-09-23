package com.echoece.favlistapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private TextView txtCategoryNumber, txtCategoryName;

    public CategoryViewHolder(@NonNull View view) {
        super(view);
        //this view refers to the view that we inflate on the recyclerview, in this case the category_viewholder. this view contains the elements
        txtCategoryNumber= view.findViewById(R.id.category_number_textview);
        txtCategoryName= view.findViewById(R.id.category_name_textview);
    }
    //getter methods, needed for setting the values there from onBindViewHolder method from adaptor class
    public TextView getTxtCategoryNumber() {
        return txtCategoryNumber;
    }

    public TextView getTxtCategoryName() {
        return txtCategoryName;
    }
}
