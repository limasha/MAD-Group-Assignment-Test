package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.os.Bundle;
import android.widget.GridView;
import java.util.ArrayList;
import java.util.List;
import static com.example.quizapp.SplashActivity.catList;


public class CategoryActivity extends AppCompatActivity {
    private GridView catGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        getSupportActionBar().setTitle("Categories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        catGrid = findViewById(R.id.catGridview);



        CatGridAdapter adapter = new CatGridAdapter(catList);
        catGrid.setAdapter(adapter);









    }
}