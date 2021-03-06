package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CatActivity extends AppCompatActivity {
    private ImageButton cpp,c,python,java,ruby,c_sharp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);
        getSupportActionBar().setTitle("Categories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cpp= findViewById(R.id.cpp);
        c= findViewById(R.id.c);
        c_sharp= findViewById(R.id.c_sharp);
        java= findViewById(R.id.java);
        ruby= findViewById(R.id.ruby);
        python= findViewById(R.id.python);





        cpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatActivity.this, SetVActivity.class);
                startActivity(intent);
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatActivity.this, SetKActivity.class);
                startActivity(intent);
            }
        });
        c_sharp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatActivity.this, SetLActivity.class);
                startActivity(intent);
            }
        });

        java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatActivity.this, SetActivity.class);
                startActivity(intent);
            }
        });

        ruby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatActivity.this, SetTActivity.class);
                startActivity(intent);
            }
        });
        python.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatActivity.this, SetDActivity.class);
                startActivity(intent);
            }
        });

    }
}