package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class SplashActivity__k extends AppCompatActivity {
    private TextView appName;

    public static List<String> catList;

    private  FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__k);
        appName = findViewById(R.id.appName);
        Typeface typeface = ResourcesCompat.getFont(this,R.font.newRoman);
        appName.setTypeface(typeface);

        Animation animation= AnimationUtils.loadAnimation(this,R.anim.animation);
        appName.setAnimation(animation);

        firestore = FirebaseFirestore.getInstance();

        new Thread(new Runnable() {
            @Override
            public void run() {
                loadData();


            }
        }).start();
    }

    private void loadData()
    {
        catList.clear();

        firestore.collection("QUIZ").document().document("Categories")
                .get().addOnCompleteListener(new onComleteListener<DocumentSnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task){
                if (task.isSuccessful())
                {
                    DocumentSnapshot doc = task.getResult();
                    if (doc.exists())
                    {
                        long count = (long)doc.get("COUNT");

                        for (int i=1; i<=count; i++)
                        {
                            String catName = doc.getString("CAT"+ String.valueOf(1));

                            catList.add(catName);
                        }
                        Intent intent = new Intent(SplashActivity__k.this,MainActivity_k.class);
                        startActivity(intent);
                        SplashActivity__k.this.finish();
                    }
                    else
                    {
                        Toast.makeText(SplashActivity__k.this,"No Catogory Document Exists!", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                }
                else
                {
                    Toast.makeText(SplashActivity__k.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}