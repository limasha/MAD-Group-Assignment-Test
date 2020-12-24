package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Question1 extends AppCompatActivity {

    RadioButton radioBtn1,radioBtn2,radioBtn3,radioBtn4;
    TextView question,question_count,score_count,timer;
    Button quit,confirm;

    int total=0;
    int correct=0;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        radioBtn1=(RadioButton)findViewById(R.id.radio_button1);
        radioBtn2=(RadioButton)findViewById(R.id.radio_button2);
        radioBtn3=(RadioButton)findViewById(R.id.radio_button3);
        radioBtn4=(RadioButton)findViewById(R.id.radio_button4);

        question=(TextView)findViewById(R.id.question);
        question_count=(TextView)findViewById(R.id.textview_question_count);
        score_count=(TextView)findViewById(R.id.text_view_score_count);
        timer=(TextView)findViewById(R.id.text_view_countdown);

        confirm=(Button)findViewById(R.id.button_confirm_next);
        quit=(Button)findViewById(R.id.button_quit_quiz);

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),QuizLevel1.class);
                startActivity(intent);

            }
        });

        updateQuestion();
        reverseTimer(200,timer);

    }

    private void updateQuestion()
    {
        total++;

        if(total<=10)
        {
            question_count.setText(String.valueOf(total));
        }

        if(total>10)
        {
            Intent i=new Intent(Question1.this,ResultActivity.class);
            i.putExtra("correct",String.valueOf(correct));
            startActivity(i);
        }
        else {
            reference= FirebaseDatabase.getInstance().getReference().child("Level 1").child(String.valueOf(total));
            reference.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    final SetQuestion setQuestion = snapshot.getValue(SetQuestion.class);

                    question.setText(setQuestion.getQuestion());
                    radioBtn1.setText(setQuestion.getOption1());
                    radioBtn2.setText(setQuestion.getOption2());
                    radioBtn3.setText(setQuestion.getOption3());
                    radioBtn4.setText(setQuestion.getOption4());

                    radioBtn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            confirm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(radioBtn1.getText().toString().equals(setQuestion.getAnswer())){

                                        radioBtn1.setTextColor(Color.parseColor("#03A9F4"));
                                        Handler handler=new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {

                                                correct++;

                                                score_count.setText(String.valueOf(correct));

                                                radioBtn1.setTextColor(Color.BLACK);
                                                radioBtn1.setChecked(false);
                                                Toast.makeText(getApplicationContext(),"Correct Answer",Toast.LENGTH_SHORT).show();

                                                updateQuestion();
                                            }
                                        },1500);
                                    }
                                    else{
                                        //answer is wrong, find the new question and show the correct answer

                                        radioBtn1.setTextColor(Color.RED);
                                        Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();

                                        if(radioBtn2.getText().toString().equals(setQuestion.getAnswer()))
                                        {
                                            radioBtn2.setTextColor(Color.parseColor("#03A9F4"));
                                        }
                                        else if(radioBtn3.getText().toString().equals(setQuestion.getAnswer()))
                                        {
                                            radioBtn3.setTextColor(Color.parseColor("#03A9F4"));
                                        }
                                        else if(radioBtn4.getText().toString().equals(setQuestion.getAnswer()))
                                        {
                                            radioBtn4.setTextColor(Color.parseColor("#03A9F4"));
                                        }

                                        Handler handler=new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                radioBtn1.setTextColor(Color.BLACK);
                                                radioBtn2.setTextColor(Color.BLACK);
                                                radioBtn3.setTextColor(Color.BLACK);
                                                radioBtn4.setTextColor(Color.BLACK);

                                                radioBtn1.setChecked(false);
                                                radioBtn2.setChecked(false);
                                                radioBtn3.setChecked(false);
                                                radioBtn4.setChecked(false);
                                                updateQuestion();

                                            }
                                        },1500);

                                    }
                                }
                            });
                                }
                            });


                    radioBtn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            confirm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    if(radioBtn2.getText().toString().equals(setQuestion.getAnswer())){

                                        radioBtn2.setTextColor(Color.parseColor("#03A9F4"));
                                        Handler handler=new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {

                                                correct++;

                                                score_count.setText(String.valueOf(correct));

                                                radioBtn2.setTextColor(Color.BLACK);
                                                radioBtn2.setChecked(false);
                                                Toast.makeText(getApplicationContext(),"Correct Answer",Toast.LENGTH_SHORT).show();

                                                updateQuestion();
                                            }
                                        },1500);
                                    }
                                    else{
                                        //answer is wrong, find the new question

                                        radioBtn2.setTextColor(Color.RED);
                                        Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();

                                        if(radioBtn1.getText().toString().equals(setQuestion.getAnswer()))
                                        {
                                            radioBtn1.setTextColor(Color.parseColor("#03A9F4"));
                                        }
                                        else if(radioBtn3.getText().toString().equals(setQuestion.getAnswer()))
                                        {
                                            radioBtn3.setTextColor(Color.parseColor("#03A9F4"));
                                        }
                                        else if(radioBtn4.getText().toString().equals(setQuestion.getAnswer()))
                                        {
                                            radioBtn4.setTextColor(Color.parseColor("#03A9F4"));
                                        }

                                        Handler handler=new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                radioBtn1.setTextColor(Color.BLACK);
                                                radioBtn2.setTextColor(Color.BLACK);
                                                radioBtn3.setTextColor(Color.BLACK);
                                                radioBtn4.setTextColor(Color.BLACK);

                                                radioBtn1.setChecked(false);
                                                radioBtn2.setChecked(false);
                                                radioBtn3.setChecked(false);
                                                radioBtn4.setChecked(false);
                                                updateQuestion();

                                            }
                                        },1500);
                                    }
                                }
                            });
                        }
                    });

                    radioBtn3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            confirm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    if(radioBtn3.getText().toString().equals(setQuestion.getAnswer())){

                                        radioBtn3.setTextColor(Color.parseColor("#03A9F4"));
                                        Handler handler=new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {

                                                correct++;

                                                score_count.setText(String.valueOf(correct));

                                                radioBtn3.setTextColor(Color.BLACK);
                                                radioBtn3.setChecked(false);
                                                Toast.makeText(getApplicationContext(),"Correct Answer",Toast.LENGTH_SHORT).show();

                                                updateQuestion();
                                            }
                                        },1500);
                                    }
                                    else{
                                        //answer is wrong, find the new question

                                        radioBtn3.setTextColor(Color.RED);
                                        Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();

                                        if(radioBtn1.getText().toString().equals(setQuestion.getAnswer()))
                                        {
                                            radioBtn1.setTextColor(Color.parseColor("#03A9F4"));
                                        }
                                        else if(radioBtn2.getText().toString().equals(setQuestion.getAnswer()))
                                        {
                                            radioBtn2.setTextColor(Color.parseColor("#03A9F4"));
                                        }
                                        else if(radioBtn4.getText().toString().equals(setQuestion.getAnswer()))
                                        {
                                            radioBtn4.setTextColor(Color.parseColor("#03A9F4"));
                                        }

                                        Handler handler=new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                radioBtn1.setTextColor(Color.BLACK);
                                                radioBtn2.setTextColor(Color.BLACK);
                                                radioBtn3.setTextColor(Color.BLACK);
                                                radioBtn4.setTextColor(Color.BLACK);

                                                radioBtn1.setChecked(false);
                                                radioBtn2.setChecked(false);
                                                radioBtn3.setChecked(false);
                                                radioBtn4.setChecked(false);
                                                updateQuestion();

                                            }
                                        },1500);

                                    }
                                }

                            });
                        }
                    });


                    radioBtn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            confirm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    if(radioBtn4.getText().toString().equals(setQuestion.getAnswer())){

                                        radioBtn4.setTextColor(Color.parseColor("#03A9F4"));
                                        Handler handler=new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {

                                                correct++;

                                                score_count.setText(String.valueOf(correct));

                                                radioBtn4.setTextColor(Color.BLACK);
                                                radioBtn4.setChecked(false);
                                                Toast.makeText(getApplicationContext(),"Correct Answer",Toast.LENGTH_SHORT).show();

                                                updateQuestion();
                                            }
                                        },1500);
                                    }
                                    else{
                                        //answer is wrong, find the new question

                                        radioBtn4.setTextColor(Color.RED);
                                        Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();

                                        if(radioBtn1.getText().toString().equals(setQuestion.getAnswer()))
                                        {
                                            radioBtn1.setTextColor(Color.parseColor("#03A9F4"));
                                        }
                                        else if(radioBtn2.getText().toString().equals(setQuestion.getAnswer()))
                                        {
                                            radioBtn2.setTextColor(Color.parseColor("#03A9F4"));
                                        }
                                        else if(radioBtn3.getText().toString().equals(setQuestion.getAnswer()))
                                        {
                                            radioBtn3.setTextColor(Color.parseColor("#03A9F4"));
                                        }

                                        Handler handler=new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                radioBtn1.setTextColor(Color.BLACK);
                                                radioBtn2.setTextColor(Color.BLACK);
                                                radioBtn3.setTextColor(Color.BLACK);
                                                radioBtn4.setTextColor(Color.BLACK);

                                                radioBtn1.setChecked(false);
                                                radioBtn2.setChecked(false);
                                                radioBtn3.setChecked(false);
                                                radioBtn4.setChecked(false);
                                                updateQuestion();

                                            }
                                        },1500);

                                    }
                                }

                            });
                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public void reverseTimer(int seconds, final TextView tv){
        new CountDownTimer(seconds * 1000+1000,1000){

            public void onTick(long millisUntilFinished){
                int seconds=(int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
            }

            @Override
            public void onFinish()
            {
                    tv.setText("Completed");
                    Intent intent = new Intent(Question1.this, ResultActivity.class);
                    intent.putExtra("total", String.valueOf(total));
                    intent.putExtra("correct", String.valueOf(correct));
                    startActivity(intent);
            }
        }.start();
    }

}