package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Entite.Enseignant;
import com.example.myapplication.Entite.MyDatabase;
import com.example.myapplication.Interface.EnseignantDao;

import java.util.ArrayList;
import java.util.List;

public class ListeEnseignants extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EnseignantAdapter enseignantAdapter;

    private List<Enseignant> enseignants = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_enseignants);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.enseignantsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize and set the adapter
        // Initialize and set the adapter
        enseignantAdapter = new EnseignantAdapter(new ArrayList<>()); // Pass a non-null list here


        // Set item click listener
        enseignantAdapter.setOnItemClickListener(new EnseignantAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (enseignants != null && position < enseignants.size()) {
                    Intent intent = new Intent(ListeEnseignants.this, ModifierEnseignant.class);
                    intent.putExtra("ENSEIGNANT_ID", enseignants.get(position).getId());
                    startActivity(intent);
                } else {
                    // Log or show a message indicating that the list is null or the position is invalid
                }
            }
        });


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


    public void onModifierButtonClick(View view) {
        EditText editTextIdEnseignant = findViewById(R.id.editTextIdEnseignant);
        String enseignantId = editTextIdEnseignant.getText().toString();

        if (!enseignantId.isEmpty()) {
            Intent intent = new Intent(ListeEnseignants.this, ModifierEnseignant.class);
            intent.putExtra("ENSEIGNANT_ID", Long.parseLong(enseignantId));
            startActivity(intent);
        } else {
            // Show a message indicating that the ID is empty
            Toast.makeText(this, "Please enter Enseignant ID", Toast.LENGTH_SHORT).show();
        }
    }

}
