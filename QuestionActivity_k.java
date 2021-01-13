package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity_k extends AppCompatActivity implements View.OnClickListener {

    private TextView queston,qCount,timer;
    private Button option1,option2,option3,option4;
    private List<Question_k> questionKList;
    private int questionNum;
    private CountDownTimer countDown;
    private int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_k);

        queston = findViewById(R.id.question);
        qCount = findViewById(R.id.question_no);
        timer = findViewById(R.id.countdown);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);

        getQuestionList();
        score=0;
    }

    private void getQuestionList()
    {
        questionKList = new ArrayList<>();

        questionKList.add(new Question_k("Question1","A","B","C","D",2));
        questionKList.add(new Question_k("Question2","B","C","D","A",2));
        questionKList.add(new Question_k("Question3","C","A","B","D",2));
        questionKList.add(new Question_k("Question4","D","B","A","C",2));
        questionKList.add(new Question_k("Question5","C","D","B","A",2));

        setQuestion();
    }

    private void setQuestion()
    {
        timer.setText(String.valueOf(10));
        queston.setText(questionKList.get(0).getQuestion());
        option1.setText(questionKList.get(0).getOptionA());
        option2.setText(questionKList.get(0).getOptionB());
        option3.setText(questionKList.get(0).getOptionC());
        option4.setText(questionKList.get(0).getOptionD());

        qCount.setText(String.valueOf(1)+"/"+ String.valueOf(questionKList.size()));
        startTimer();
        questionNum=0;
    }

    private  void startTimer()
    {
         countDown = new CountDownTimer(12000,1000) {
            @Override
            public void onTick(long millisUntilFinished){
                if(millisUntilFinished<10000)
                timer.setText(String.valueOf(millisUntilFinished/1000));
            }
            @Override
            public  void  onFinish(){
                changeQuestion();
            }
        };
        countDown.start();
    }



    @Override
    public void onClick(View v) {
        int selectedOption =0;
        switch (v.getId())
        {
            case R.id.option1:
                selectedOption =1;
            break;
            case R.id.option2:
                selectedOption =2;
                break;
            case R.id.option3:
                selectedOption =3;
                break;
            case R.id.option4:
                selectedOption =4;
                break;
            default:
        }

        countDown.cancel();
        checkAnswer(selectedOption,v);

    }
    private void checkAnswer(int selectedOption, View view)
    {
        if(selectedOption == questionKList.get(questionNum).getCorrectAns())
        {
            //Right Answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            score++;
        }
        else
        {
            //wrong Answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.RED));

            switch (questionKList.get(questionNum).getCorrectAns())
            {
                case 0:
                    option1.setBackgroundTintList((ColorStateList.valueOf(Color.GREEN)));
                    break;
                case 1:
                    option2.setBackgroundTintList((ColorStateList.valueOf(Color.GREEN)));
                    break;
                case 2:
                    option3.setBackgroundTintList((ColorStateList.valueOf(Color.GREEN)));
                    break;
                case 3:
                    option4.setBackgroundTintList((ColorStateList.valueOf(Color.GREEN)));
                    break;
            }
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeQuestion();
            }
        }, 2000);



    }

    private void changeQuestion()
    {
        if(questionNum < questionKList.size() - 1)
        {
            playAnim(queston,0,0);
            playAnim(option1,0,1);
            playAnim(option2,0,2);
            playAnim(option3,0,3);
            playAnim(option4,0,4);

            qCount.setText(String.valueOf(questionNum+1)+"/"+String.valueOf(questionKList.size()));

            timer.setText(String.valueOf(10));
            startTimer();


        }
        else
        {
            //Go to score Activity
            Intent intent = new Intent(QuestionActivity_k.this,ScoreActivity_k.class);
            intent.putExtra("SCORE",String.valueOf(score)+ "/" + String.valueOf(questionKList.size()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            QuestionActivity_k.this.finish();
        }
    }

    private void playAnim(View view, final int value, int viewNum)
    {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).setInterpolator(new DecelerateInterpolator()).
                setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (value==0)
                        {
                            switch (viewNum)
                            {
                                case 0:
                                    ((TextView)view).setText(questionKList.get(questionNum).getQuestion());
                                    break;
                                case 1:
                                    ((Button)view).setText(questionKList.get(questionNum).getOptionA());
                                    break;
                                case 2:
                                    ((Button)view).setText(questionKList.get(questionNum).getOptionB());
                                    break;
                                case 3:
                                    ((Button)view).setText(questionKList.get(questionNum).getOptionC());
                                    break;
                                case 4:
                                    ((Button)view).setText(questionKList.get(questionNum).getOptionD());
                                    break;

                            }

                            if(viewNum !=0)
                            {
                                ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("purple_700")));
                            }
                            playAnim(view,1,viewNum);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        countDown.cancel();
    }
}