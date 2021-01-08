package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddQuestionActivity extends AppCompatActivity {

    Button question1Btn,question2Btn,question3Btn,addNewquestionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        question1Btn=(Button)findViewById(R.id.button1);
        question2Btn=(Button)findViewById(R.id.button2);
        question3Btn=(Button)findViewById(R.id.button3);
        addNewquestionBtn=(Button)findViewById(R.id.button_add_question);

        addNewquestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}