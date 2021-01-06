package com.example.quiz_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;


public class activity_cat_t extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);

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

        activity_CatAdapter_t adapter_t = new activity_CatAdapter_t(catList);
        cateGrid.setAdapter((ListAdapter) adapter_t);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            activity_cat_t.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}