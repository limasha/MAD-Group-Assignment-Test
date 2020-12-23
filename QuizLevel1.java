package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuizLevel1 extends AppCompatActivity {

    Button startbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_level1);

        Bundle extras=getIntent().getExtras();
        String value1=extras.getString("Value1");

        Toast.makeText(getApplicationContext(),value1,Toast.LENGTH_LONG).show();


        startbtn=(Button)findViewById(R.id.button_start);
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Question1.class);
                startActivity(intent);
            }
        });
    }
}