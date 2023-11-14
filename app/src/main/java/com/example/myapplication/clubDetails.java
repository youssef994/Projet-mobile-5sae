package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Entite.Club;
import com.example.myapplication.Entite.Enseignant;
import com.example.myapplication.Entite.MyDatabase;
import com.example.myapplication.Interface.ClubDao;
import com.example.myapplication.Interface.EnseignantDao;

public class clubDetails extends AppCompatActivity {

    private ClubDao clubDao;
    private long clubId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_details);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("CLUB_ID")) {
            clubId = intent.getIntExtra("CLUB_ID", -1);
        }

        // Initialize the database instance
        clubDao = MyDatabase.getInstance(this).clubDao();

        // Perform database operations in an AsyncTask
        new LoadClubTask().execute();

        // Assuming you have a "Modifier" button that triggers the update
        findViewById(R.id.buttonModifier).setOnClickListener(v -> updateClubData());

        Button deleteButton = findViewById(R.id.buttonDelete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle delete button click
                new DeleteClubTask().execute();
            }
        });

    }


    private void updateClubData() {
        EditText nomEditText = findViewById(R.id.nomEditText);
        EditText presidentEditText = findViewById(R.id.presidentEditText);
        EditText vicepEditText = findViewById(R.id.vicepEditText);
        EditText descriptionEditText = findViewById(R.id.descriptionEditText);

        // Assuming you have retrieved the updated values from your EditText fields
        String updatedNom = nomEditText.getText().toString();
        String updatepresidant = presidentEditText.getText().toString();
        String updatevicep = vicepEditText.getText().toString();
        String updatedDescription = descriptionEditText.getText().toString();

// Create an updated Enseignant object with the new values
        Club updatedClub = new Club();
        updatedClub.setId((int) clubId);
        updatedClub.setNom(updatedNom);
        updatedClub.setPresident(updatepresidant);//setPrenom(updatedPrenom);
        updatedClub.setVicep(updatevicep);
        updatedClub.setDescription(updatedDescription);

// Perform the update in the background thread
        new UpdatClubTask().execute(updatedClub);

    }




    private class LoadClubTask extends AsyncTask<Void, Void, Club> {

        @Override
        protected Club doInBackground(Void... voids) {
            // Perform database query on a background thread
            return clubDao.getClubById(clubId);//getById(enseignantId);
        }

        @Override
        protected void onPostExecute(Club club) {
            super.onPostExecute(club);

            // Update UI with the retrieved enseignant data
            if (club != null) {
                // Update your UI components here
                EditText nomEditText = findViewById(R.id.nomEditText);
                EditText presidentEditText = findViewById(R.id.presidentEditText);
                EditText vicepEditText = findViewById(R.id.vicepEditText);
                EditText descriptionEditText = findViewById(R.id.descriptionEditText);

                nomEditText.setText(club.getNom());
                presidentEditText.setText(club.getPresident());
                vicepEditText.setText(club.getVicep());
                descriptionEditText.setText(club.getDescription());
            }
        }
    }



    private class DeleteClubTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            // Perform database delete operation on a background thread
            Club clubToDelete = clubDao.getClubById(clubId);//getEnseignantById(enseignantId);
            if (clubToDelete != null) {
                clubDao.delete(clubToDelete);
            }
            return null;
        }



        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            // Redirect back to ListeEnseignants activity
            Intent intent = new Intent(clubDetails.this, ListeClubs.class);
            startActivity(intent);
            finish(); // Finish the current activity to prevent going back to it with outdated data
        }
    }

    private class UpdatClubTask extends AsyncTask<Club, Void, Void> {

        @Override
        protected Void doInBackground(Club... clubs) {
            // Perform database update on a background thread
            clubDao.update(clubs[0]);
            return null;
        } @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            // Redirect back to ListeEnseignants activity
            Intent intent = new Intent(clubDetails.this, ListeClubs.class);
            startActivity(intent);
            finish(); // Finish the current activity to prevent going back to it with outdated data
        }}
}
