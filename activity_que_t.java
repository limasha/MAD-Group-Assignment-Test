package com.example.quiz_app;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class activity_que_t extends AppCompatActivity implements View.OnClickListener {

    private TextView question,QueCount,Timer;
    private CheckBox Option1,Option2,Option3,Option4;
    private List<Question_t_Ruby> questionList;
    private int questionNum;
    private CountDownTimer countdown;
    private final Intent activity_score_t;
    private int score;

    public activity_que_t(Intent activity_score_t) {
        this.activity_score_t = activity_score_t;
    }

    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que_t);
        
        question=findViewById(R.id.textView);
        QueCount=findViewById(R.id.textView2);
        Timer=findViewById(R.id.textView1);
        
        Option1=findViewById(R.id.checkBox);
        Option2=findViewById(R.id.checkBox3);
        Option3=findViewById(R.id.checkBox4);
        Option4=findViewById(R.id.checkBox5);

        Option1.setOnClickListener(this);
        Option2.setOnClickListener(this);
        Option3.setOnClickListener(this);
        Option4.setOnClickListener(this);

        getQuestionsList();

        score=0;
    }

    private void getQuestionsList() {

        questionList=new ArrayList<>();

        questionList.add(new Question_t_Ruby(" 1.) What is the output of below code ? x = 20      x *=6     puts x     =begin     This is the 1 st      question you      take.     =end      \"","120","124","126","129",1));
        questionList.add(new Question_t_Ruby("2.) What is the explanation for global variable in Ruby?","These variables start with @@ and accessible by all instances of the class that is defined in."," These variables start with $ and its scope is available for the entire Ruby program.","These variables start with a lower case letter or an underscore and scope are within the function or code construct where it is declared.","These variables start with @ and similar to class variables except class variables are local to a single instance of a class.",2));
        questionList.add(new Question_t_Ruby("Question 3","A","C","B","D",1));
        questionList.add(new Question_t_Ruby("Question 4","C","B","A","D",4));
        questionList.add(new Question_t_Ruby("Question 5","A","C","D","B",3));
        questionList.add(new Question_t_Ruby("Question 6","B","C","D","A",2));
        questionList.add(new Question_t_Ruby("Question 7","C","D","A","B",2));
        questionList.add(new Question_t_Ruby("Question 8","A","C","D","B",1));
        questionList.add(new Question_t_Ruby("Question 9","D","B","A","C",2));
        questionList.add(new Question_t_Ruby("Question 10","B","C","A","D",1));

        setQuestion();
    }

    @SuppressLint("SetTextI18n")
    private void setQuestion() {
        Timer.setText(String.valueOf(10));

        question.setText(questionList.get(0).getQuestion());
        Option1.setText(questionList.get(0).getOptionA());
        Option2.setText(questionList.get(0).getOptionB());
        Option3.setText(questionList.get(0).getOptionC());
        Option4.setText(questionList.get(0).getOptionD());

        QueCount.setText(1 + "/" + questionList.size());

        startTimer();

        questionNum=0;
    }

    private void startTimer() {
         countdown = new CountDownTimer(12000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                if (millisUntilFinished<10)
                Timer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                changeQuestion();
            }
        };

    }



    @SuppressLint({"NewApi", "NonConstantResourceId"})
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Option1:

            case R.id.Option2:

            case R.id.Option3:

            case  R.id.Option4:
                break;

            default:
        }

         countdown.cancel();

        Object selectedOption = new Object();
        checkAnswer(selectedOption,v);

    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private void checkAnswer(Object selectedOption, View view) {

        if (selectedOption == questionList.get(questionNum).getClass()) {

            //right answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            score++;

        } else {
            //wrong answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.RED));

            switch (questionList.get(questionNum).getCorrectAns())
            {
                case 1:
                    Option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 2:
                    Option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 3:
                    Option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 4:
                    Option4.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;

            }
        }
        Handler handler=new Handler();
        handler.postDelayed(this::changeQuestion,2000);



    }

    @SuppressLint("SetTextI18n")
    public void changeQuestion()

    {
        if (questionNum< (questionList.size()))
        {
            questionNum++;

            playAnim(question,0,0);
            playAnim(Option1,0,1);
            playAnim(Option2,0,2);
            playAnim(Option3,0,3);
            playAnim(Option4,0,4);

            QueCount.setText((questionNum + 1) + "/" + questionList.size());

            Timer.setText(String.valueOf(10));

            startTimer();

        }
        else
        {
           //Move to score Activity
            Intent intent = new Intent(activity_que_t.this.activity_score_t);
            intent.putExtra("Score", score + "/" + questionList.size());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            //activity_que_t.this.finish();
        }
    }

    private void playAnim(View view, final int value,int viewNum) {

        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500)
                .setStartDelay(100).setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                         if(value==0)
                         {
                           switch(viewNum)
                           {
                               case 0:
                                   ((TextView)view).setText(questionList.get(questionNum).getQuestion());
                                   break;
                               case 1:
                                   ((Button)view).setText(questionList.get(questionNum).getOptionA());
                                   break;
                               case 2:
                                   ((Button)view).setText(questionList.get(questionNum).getOptionB());
                                   break;
                               case 3:
                                   ((Button)view).setText(questionList.get(questionNum).getOptionC());
                                   break;
                               case 4:
                                   ((Button)view).setText(questionList.get(questionNum).getOptionD());
                                   break;

                           }

                           if(viewNum!=0)
                               ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));

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
    public void onBackPressed(){
        super.onBackPressed();

        countdown.cancel();
    }

}
