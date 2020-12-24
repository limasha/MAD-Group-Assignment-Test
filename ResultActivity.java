package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView score;
    Button doneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        score=(TextView)findViewById(R.id.score);

        Intent i=getIntent();

        String correct=i.getStringExtra("correct");

        score.setText(correct);

        doneBtn=(Button)findViewById(R.id.button_done);

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),CSharpQuiz.class);
                startActivity(intent);
            }
        });
    }
}