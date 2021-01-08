package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class LevelActivity extends AppCompatActivity {

    Button button1,button2,button3,addLevBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

            button1=(Button)findViewById(R.id.button1);
            button2=(Button)findViewById(R.id.button2);
            button3=(Button)findViewById(R.id.button3);
            addLevBtn=(Button)findViewById(R.id.button_level);

        }

}