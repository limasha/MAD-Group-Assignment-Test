package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArrayMap;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.quizapp.CategoryAdminActivity.catList;
import static com.example.quizapp.CategoryAdminActivity.selected_cat_index;
import static com.example.quizapp.SetAdminActivity.selected_set_index;
import static com.example.quizapp.SetAdminActivity.setsIDs;

public class QuestionsAdminActivity extends AppCompatActivity {
    private RecyclerView quesView;
    private Button addQB;
    public static List<QuestionModel> quesList = new ArrayList<>();
    private QuestionAdapter adapter;
    private FirebaseFirestore firestore;
    private Dialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_admin);

        getSupportActionBar().setTitle("Questions");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        quesView = findViewById(R.id.quest_recycler);
        addQB = findViewById(R.id.addQB);

        loadingDialog = new Dialog(QuestionsAdminActivity.this);
        loadingDialog.setContentView(R.layout.loading_progressbar);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawableResource(R.drawable.progress_background);
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        addQB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionsAdminActivity.this, QuestionDetailsActivity.class);
                intent.putExtra("ACTION","ADD");
                startActivity(intent);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        quesView.setLayoutManager(layoutManager);

        firestore = FirebaseFirestore.getInstance();

        loadQuestions();

    }

    private void loadQuestions()
    {
        quesList.clear();

        loadingDialog.show();
        firestore.collection("Quiz").document("CAT"+String.valueOf(selected_cat_index))
                .collection("SET"+(selected_set_index)).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                QuerySnapshot questions=task.getResult();

                                for (QueryDocumentSnapshot doc : questions) {

                                    for(int i=0; i <quesList.size() ; i++)
                                    {
                                        String quesID = "QUESTION" + String.valueOf(i+1);



                                        quesList.add(new QuestionModel(
                                                quesID,
                                                doc.getString("QUESTION"),
                                                doc.getString("A"),
                                                doc.getString("B"),
                                                doc.getString("C"),
                                                doc.getString("D"),
                                                Integer.valueOf(doc.getString("ANSWER"))
                                        ));

                                    }





                                }

                                adapter = new QuestionAdapter(quesList);
                                quesView.setAdapter(adapter);

                                loadingDialog.dismiss();



                            }

                           /* Map<String, QueryDocumentSnapshot> docList = new ArrayMap<>();

                            for(QueryDocumentSnapshot doc : queryDocumentSnapshots)
                            {
                                docList.put(doc.getId(),doc);
                            }



                            for(int i=0; i <quesList.size() ; i++)
                            {
                                String quesID = "QUESTION" + String.valueOf(i+1);

                                QueryDocumentSnapshot quesDoc = docList.get(quesID);

                                quesList.add(new QuestionModel(
                                        quesID,
                                        quesDoc.getString("QUESTION"),
                                        quesDoc.getString("A"),
                                        quesDoc.getString("B"),
                                        quesDoc.getString("C"),
                                        quesDoc.getString("D"),
                                        Integer.valueOf(quesDoc.getString("ANSWER"))
                                ));

                            }*/


                        }



                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(QuestionsAdminActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        loadingDialog.dismiss();
                    }
                });

    }
    protected void onResume() {
        super.onResume();

        if(adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}

