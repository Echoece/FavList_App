package com.echoece.favlistapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryRecyclerAdaptor extends RecyclerView.Adapter<CategoryViewHolder> {
    //String[] categories={"hobbies","sports","games","electronic Gadgets","foods","favourite countries","random","gibrish","boo","moo"};
    private ArrayList<Category> categories;

    public CategoryRecyclerAdaptor(ArrayList<Category> categories) {
        this.categories = categories;
    }

    //this is called when we need to let the recyclerview know about the viewholder
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        /* LayoutInflater is used to create a new View (or Layout) object from one of your xml layouts.
        The most common time most people use it is in a RecyclerView. (See these RecyclerView examples for a list or a grid.)
        You have to inflate a new layout for every single visible item in the list or grid. */

        /* You also can use a layout inflater if you have a complex layout that you want to add programmatically (like we did in our example).
        You could do it all in code, but it is much easier to define it in xml first and then just inflate it. */

        //the parent refers to the activity the recyclers view will be shown on. that's why we pass its context as parameter
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());

        //final argument is false because recyclerview automatically attach and remove the item from root by itself
        View view=layoutInflater.inflate(R.layout.category_viewholder,parent,false);

        return new CategoryViewHolder(view);
    }

    //it let us bind the data to the recycler view and let the recycler view show the data that we get from database.
    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.getTxtCategoryNumber().setText(Integer.toString(position+1));
        holder.getTxtCategoryName().setText(categories.get(position).getName());
    }

    //this method let recyclerview know how many items are there in the list
    @Override
    public int getItemCount() {
        return categories.size();
    }

    //updating the arraylist each time user puts a new value dynamically
    public void addCategory(Category category){
        categories.add(category);
        //notify the adapter about the new item. the position is tricky here. Since the size returns for example 5 . but the index will be 4;
        notifyItemInserted(categories.size()-1);
    }


}
