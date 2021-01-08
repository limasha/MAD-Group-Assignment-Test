package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddQuestion1 extends AppCompatActivity {

    EditText question,option1,option2,option3,option4,answer;
    Button insertQuestionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question1);

        question=(EditText)findViewById(R.id.editview_question);
        option1=(EditText)findViewById(R.id.editview_option1);
        option2=(EditText)findViewById(R.id.editview_option2);
        option3=(EditText)findViewById(R.id.editview_option3);
        option4=(EditText)findViewById(R.id.editview_option4);
        answer=(EditText)findViewById(R.id.editview_answer);


    }
}