package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;


public class Category_activity_k extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_k);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Categories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GridView cateGrid = findViewById(R.id.Gridview);

        List<String> catList = new ArrayList<>();

        catList.add("Java");
        catList.add("C#");
        catList.add("C");
        catList.add("Python");
        catList.add("Ruby");
        catList.add("C++");

        CategoryAdapter_k adapter_k = new CategoryAdapter_k(catList);
        cateGrid.setAdapter(adapter_k);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if (item.getItemId() == android.R.id.home)
       {
           Category_activity_k.this.finish();
       }
        return super.onOptionsItemSelected(item);
    }
}