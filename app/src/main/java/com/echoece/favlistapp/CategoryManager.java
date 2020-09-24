package com.echoece.favlistapp;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

/*implementation 'androidx.preference:preference:1.1.1' have to do this in gradle file to get the androidx version of sharedpreference
 */
public class CategoryManager {
    //context variable for passing to the shared preference
    private Context mContext;

    public CategoryManager(Context context) {
        mContext = context;
    }

    public void saveCategory(Category category){

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        //converting an arrayliste of string to a set of string
        HashSet itemHashSet=new HashSet(Arrays.asList(category.getItems()));
        //set is like an arraylist, but all values must be unique. there cant be any duplicate.its stored in a key-value pair.
        editor.putStringSet(category.getName(),itemHashSet);
        editor.apply();
    }

    public ArrayList<Category> retrieveCategories(){
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(mContext);
        // ? here means the value can be of any type. we used map because getAll() method returns a map.
        Map<String,?> data=sharedPreferences.getAll();

        ArrayList<Category> categories=new ArrayList<>();
        //iterating through the Map of data from sharedpreference
        for ( Map.Entry<String, ?> entry :data.entrySet() ) {
            Category category= new Category( entry.getKey(), new ArrayList<String>( (HashSet) entry.getValue() ) );
            categories.add(category);
        }
        return categories;
    }
}

