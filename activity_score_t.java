package com.example.quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_score_t extends AppCompatActivity {

    private final Intent activity_set_t;

    public activity_score_t(Intent activity_set_t) {
        this.activity_set_t = activity_set_t;
    }

    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_t);

        TextView score = findViewById(R.id.textView5);
        Button complete = findViewById(R.id.button7);

        String score_str =getIntent().getStringExtra("Score");
        score.setText(score_str);

        complete.setOnClickListener(v -> {
            Intent intent = new Intent(activity_score_t.this.activity_set_t);
            activity_score_t.this.startActivity(intent);
            activity_score_t.this.finish();
        });
    }

}
