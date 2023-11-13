package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.Entite.Classe;
import com.example.myapplication.Entite.Enseignant;
import com.example.myapplication.Entite.MyDatabase;
import com.example.myapplication.Interface.ClasseDao;
import com.example.myapplication.Interface.EnseignantDao;

import java.util.ArrayList;
import java.util.List;

public class ListeClasse extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ClasseAdapter classeAdapter;
    private List<Classe> classes = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_classe);
        // Initialize RecyclerView
        recyclerView = findViewById(R.id.classesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize and set the adapter
        // Initialize and set the adapter
        classeAdapter = new ClasseAdapter(new ArrayList<>()); // Pass a non-null list here






        recyclerView.setAdapter(classeAdapter);

        // Load the list of classes from your Room database
        loadClasses();

    }
    private void loadClasses() {
        // Use a background thread to retrieve the list of classes
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Retrieve the list of classes from the Room database
                ClasseDao classeDao = MyDatabase.getInstance(ListeClasse.this).classeDao();
                final List<Classe> classes = classeDao. getAllClasses();
                // Update the adapter with the list of classes on the main thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        classeAdapter.setClasses(classes);
                    }
                });
            }
        }).start();
}}