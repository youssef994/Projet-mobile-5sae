package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Entite.Evaluation;
import com.example.myapplication.Entite.MyDatabase;
import com.example.myapplication.Interface.EvaluationDao;
import java.util.ArrayList;
import java.util.List;

public class ListeEvaluations extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EvaluationAdapter evaluationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_evaluations);

        recyclerView = findViewById(R.id.evaluationsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize and set the adapter
        evaluationAdapter = new EvaluationAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(evaluationAdapter);

        // Set item click listener for the RecyclerView
        evaluationAdapter.setOnItemClickListener(new EvaluationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle item click, e.g., navigate to the detail view
                Evaluation clickedEvaluation = evaluationAdapter.getEvaluation(position);

                // Assuming you have an EvaluationDetail activity
                Intent intent = new Intent(ListeEvaluations.this, EvaluationDetail.class);
                intent.putExtra("EVALUATION_ID", clickedEvaluation.getId());
                startActivity(intent);
            }
        });

        // Load the list of evaluations from your Room database
        loadEvaluations();
    }

    private void loadEvaluations() {
        // Use a background thread to retrieve the list of evaluations
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Retrieve the list of evaluations from the Room database
                EvaluationDao evaluationDao = MyDatabase.getInstance(ListeEvaluations.this).evaluationDao();
                final List<Evaluation> evaluations = evaluationDao.getAllEvaluations();

                // Update the adapter with the list of evaluations on the main thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        evaluationAdapter.setEvaluations(evaluations);
                    }
                });
            }
        }).start();
    }
}
