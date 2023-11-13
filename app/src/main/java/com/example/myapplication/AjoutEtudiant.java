package com.example.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Entite.Classe;
import com.example.myapplication.Entite.Etudiant;
import com.example.myapplication.Entite.MyDatabase;
import com.example.myapplication.Interface.ClasseDao;
import com.example.myapplication.Interface.EtudiantDao;

public class AjoutEtudiant extends AppCompatActivity {

    private EditText editTextNomClasse;
    private EditText editTextNom;
    private EditText editTextPrenom;
    private EditText editTextEmail;
    private EditText editTextNiveau;
    private EditText editTextidentifiant;

    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_etudiant);

        editTextNomClasse = findViewById(R.id.editTextNomClasse);
        editTextNom = findViewById(R.id.editTextText2);
        editTextPrenom = findViewById(R.id.editTextText3);
        editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        editTextNiveau = findViewById(R.id.editTextNiveau);
        editTextidentifiant = findViewById(R.id.editTextidentifiant);

        addButton = findViewById(R.id.button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve class name from editTextNomClasse
                String nomClasse = editTextNomClasse.getText().toString();

                // Retrieve other student details
                String nom = editTextNom.getText().toString();
                String prenom = editTextPrenom.getText().toString();
                String email = editTextEmail.getText().toString();
                String niveau = editTextNiveau.getText().toString();
                String identifiant = editTextidentifiant.getText().toString();

                // Use AsyncTask to perform database operations in the background
                new InsertAsyncTask(AjoutEtudiant.this, nomClasse, nom, prenom, email, niveau, identifiant).execute();
            }
        });
    }

    private static class InsertAsyncTask extends AsyncTask<Void, Void, Void> {
        private Context context;
        private String nomClasse, nom, prenom, email, niveau, identifiant;

        public InsertAsyncTask(Context context, String nomClasse, String nom, String prenom, String email, String niveau, String identifiant) {
            this.context = context;
            this.nomClasse = nomClasse;
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
            this.niveau = niveau;
            this.identifiant = identifiant;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Get the class by name
            ClasseDao classeDao = MyDatabase.getInstance(context).classeDao();
            Classe classe = classeDao.getClasseByName(nomClasse);

            if (classe != null) {
                // Create and insert the student with the assigned classId
                Etudiant etudiant = new Etudiant(nom, prenom, email, identifiant, niveau, classe.getId());
                EtudiantDao etudiantDao = MyDatabase.getInstance(context).etudiantDao();
                etudiantDao.insert(etudiant);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(context, "Etudiant ajouté avec succès", Toast.LENGTH_SHORT).show();
        }
    }
}
