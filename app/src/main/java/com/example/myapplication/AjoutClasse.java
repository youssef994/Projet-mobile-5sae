package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Entite.Classe;
import com.example.myapplication.Entite.Enseignant;
import com.example.myapplication.Entite.MyDatabase;
import com.example.myapplication.Interface.ClasseDao;
import com.example.myapplication.Interface.EnseignantDao;

public class AjoutClasse extends AppCompatActivity {
    private EditText nomEditText;
    private EditText specialiteEditText;
    private EditText numeroEditText;

    private Button ajouterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_classe);


        nomEditText = findViewById(R.id.editTextText2);
        specialiteEditText = findViewById(R.id.editTextText3);
        numeroEditText = findViewById(R.id.editTextTextEmailAddress);

        ajouterButton = findViewById(R.id.button);
        ajouterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get values from EditText fields
                String nom = nomEditText.getText().toString();
                String specialite = specialiteEditText.getText().toString();
                int numero = Integer.parseInt(numeroEditText.getText().toString());


                // Create an Enseignant object
                Classe classe = new Classe();
                classe.setNom(nom);
                classe.setSpecialite(specialite);
                classe.setNumero(numero);


                // Use Kotlin Coroutines to insert the Enseignant object on a background thread
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        ClasseDao classeDao = MyDatabase.getInstance(AjoutClasse.this).classeDao();
                        classeDao.insert(classe);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        // Provide user feedback for successful insertion
                        Toast.makeText(AjoutClasse.this, "Classe ajouté avec succès", Toast.LENGTH_SHORT).show();
                    }
                }.execute();
            }


    });
}
}