package com.example.quizapp;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import static com.example.quizapp.SplashActivity.catList;
import static com.example.quizapp.SplashActivity.selected_cat_index;


public class SetActivity extends AppCompatActivity {
    private GridView sets_grid;
    private Dialog loadingDialog;
    private FirebaseFirestore firestore;
public static int CAT_ID;
    public static List<String> setsIDs = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

       String title=getIntent().getStringExtra("Catagories");
       CAT_ID= getIntent().getIntExtra("CAT_ID", 1);

        sets_grid = findViewById(R.id.sets_gridview);


        loadingDialog = new Dialog(SetActivity.this);
        loadingDialog.setContentView(R.layout.loading_progressbar);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingDialog.show();

        firestore = FirebaseFirestore.getInstance();
        loadSets();
    }

    public void loadSets()
    {

        setsIDs.clear();

        firestore.collection("Quiz").document("CAT"+String.valueOf(CAT_ID))
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {

            public void onSuccess(DocumentSnapshot documentSnapshot) {

                long noOfSets = (long)documentSnapshot.get("SETS");

                for(int i=1; i <= noOfSets; i++)
                {
                    setsIDs.add(documentSnapshot.getString("SET" + String.valueOf(i) ));
                }

                SetsAdapter adapter = new SetsAdapter(setsIDs.size());
                sets_grid.setAdapter(adapter);

                loadingDialog.dismiss();

            }
        })
                .addOnFailureListener(new OnFailureListener() {

                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SetActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        loadingDialog.dismiss();
                    }
                });


    }


    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home)
        {
            SetActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}