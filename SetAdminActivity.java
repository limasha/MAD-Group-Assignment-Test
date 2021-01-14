package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArrayMap;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.quizapp.CategoryAdminActivity.catList;
import static com.example.quizapp.CategoryAdminActivity.selected_cat_index;



public class SetAdminActivity extends AppCompatActivity {

    private RecyclerView setsView;
    private Button addSetB;
    private SetAdminAdapter adapter;
    private FirebaseFirestore firestore;
    private Dialog loadingDialog;

    public static List<String> setsIDs = new ArrayList<>();
    public static int selected_set_index=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_admin);


        getSupportActionBar().setTitle("Question Sets");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setsView = findViewById(R.id.sets_recycler);
        addSetB = findViewById(R.id.addSetB);

        loadingDialog = new Dialog(SetAdminActivity.this);
        loadingDialog.setContentView(R.layout.loading_progressbar);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawableResource(R.drawable.progress_background);
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        addSetB.setText("ADD NEW SET");

        addSetB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addNewSet();
            }
        });

        firestore = FirebaseFirestore.getInstance();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        setsView.setLayoutManager(layoutManager);

        loadSets();



    }


    private void loadSets()
    {


        setsIDs.clear();

        loadingDialog.show();
        firestore.collection("Quiz").document("CAT"+String.valueOf(selected_cat_index))
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {

            public void onSuccess(DocumentSnapshot documentSnapshot) {

                long noOfSets = (long)documentSnapshot.get("SETS");

                for(int i=1; i <= noOfSets; i++)
                {
                    setsIDs.add(documentSnapshot.getString("SET" + String.valueOf(i) ));
                }

                adapter = new SetAdminAdapter(setsIDs);
                setsView.setAdapter(adapter);

                loadingDialog.dismiss();

            }
        })



                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SetAdminActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        loadingDialog.dismiss();
                    }
                });

    }

    private void addNewSet()
    {
       loadingDialog.show();

        final String curr_cat_id = catList.get(selected_cat_index).getId();
        final String curr_counter = catList.get(selected_cat_index).getSetCounter();

        Map<String,Object> qData = new ArrayMap<>();
        qData.put("COUNT","0");

        firestore.collection("QUIZ").document(curr_cat_id)
                .collection(curr_counter).document("QUESTIONS_LIST")
                .set(qData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {

                    @Override
                    public void onSuccess(Void aVoid) {

                        Map<String,Object> catDoc = new ArrayMap<>();
                        catDoc.put("COUNTER", String.valueOf(Integer.valueOf(curr_counter) + 1)  );
                        catDoc.put("SET" + String.valueOf(setsIDs.size() + 1) , curr_counter);
                        catDoc.put("SETS", setsIDs.size() + 1);

                        firestore.collection("Quiz").document(curr_cat_id)
                                .update(catDoc)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        Toast.makeText(SetAdminActivity.this, " Set Added Successfully",Toast.LENGTH_SHORT).show();

                                        setsIDs.add(curr_counter);
                                        catList.get(selected_cat_index).setNoOfSets(String.valueOf(setsIDs.size()));
                                        catList.get(selected_cat_index).setSetCounter(String.valueOf(Integer.valueOf(curr_counter) + 1));

                                        adapter.notifyItemInserted(setsIDs.size());
                                        loadingDialog.dismiss();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(SetAdminActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                        loadingDialog.dismiss();
                                    }
                                });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SetAdminActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        loadingDialog.dismiss();
                    }
                });

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