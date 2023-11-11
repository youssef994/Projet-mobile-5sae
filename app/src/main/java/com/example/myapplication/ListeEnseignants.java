package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Entite.Enseignant;
import com.example.myapplication.Entite.MyDatabase;
import com.example.myapplication.Interface.EnseignantDao;

import java.util.List;

public class ListeEnseignants extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EnseignantAdapter enseignantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_enseignants);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.enseignantsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize and set the adapter
        enseignantAdapter = new EnseignantAdapter();
        recyclerView.setAdapter(enseignantAdapter);

        // Load the list of enseignants from your Room database
        loadEnseignants();
    }

    private void loadEnseignants() {
        // Use a background thread to retrieve the list of enseignants
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Retrieve the list of enseignants from the Room database
                EnseignantDao enseignantDao = MyDatabase.getInstance(ListeEnseignants.this).enseignantDao();
                final List<Enseignant> enseignants = enseignantDao.getAllEnseignants();

                // Update the adapter with the list of enseignants on the main thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        enseignantAdapter.setEnseignants(enseignants);
                    }
                });
            }
        }).start();
    }

}
