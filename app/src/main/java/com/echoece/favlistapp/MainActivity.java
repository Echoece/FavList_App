package com.echoece.favlistapp;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/*shared preferences are a way to store data quickly on android device, its not secure and shouldnt be used for heavy application. we can use it
  when we dont care about the data or its secuirity. more advanced way of saving data is sqlite/firebase etc.*/
public class MainActivity extends AppCompatActivity {
    private RecyclerView categoryRecyclerView;
    private CategoryManager mCategoryManager=new CategoryManager(MainActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //retrieve categories from the category manager
        ArrayList<Category> categories=mCategoryManager.retrieveCategories();


        //reference to the recyclerview
        categoryRecyclerView=findViewById(R.id.category_recyclerview);
        //setting the adaptor for the recyclerview
        categoryRecyclerView.setAdapter(new CategoryRecyclerAdaptor(categories));
        //setLayoutManager is used to specify how we want to show the items inside the recyclerview.
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        FloatingActionButton fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayCreateCategory();
            }
        });

    }
    //
    private void displayCreateCategory(){
        //setting the values from the string.xml
        String alertTitle= getString(R.string.create_category);
        String positiveButtoon = getString(R.string.button_create);
        //creating the editext
        final EditText categoryEditText=new EditText(MainActivity.this);
        categoryEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        //creating the alertbuilder
        AlertDialog.Builder alertBuilder= new AlertDialog.Builder(MainActivity.this);

        //settin up the alertbuilder
        alertBuilder.setTitle(alertTitle);
        alertBuilder.setView(categoryEditText);

        //onclicklistener for the positive button
        alertBuilder.setPositiveButton(positiveButtoon, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Category category=new Category(categoryEditText.getText().toString(),new ArrayList<String>());
                //savinth the category to the sharedpreference
                mCategoryManager.saveCategory(category);
                //
                CategoryRecyclerAdaptor categoryRecyclerAdaptor=(CategoryRecyclerAdaptor) categoryRecyclerView.getAdapter();
                categoryRecyclerAdaptor.addCategory(category);
                dialogInterface.dismiss();
            }
        });

        //creating and showing the alerbuilder
        alertBuilder.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //unnecessary  part below
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}