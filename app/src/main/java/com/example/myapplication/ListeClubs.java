package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.Entite.Club;
import com.example.myapplication.Entite.Enseignant;
import com.example.myapplication.Entite.MyDatabase;
import com.example.myapplication.ClubAdapter;
import com.example.myapplication.Interface.ClubDao;
import com.example.myapplication.Interface.EnseignantDao;

import java.util.ArrayList;
import java.util.List;

public class ListeClubs extends AppCompatActivity {
   private RecyclerView recyclerView;
    private ClubAdapter clubAdapter;


    private List<Club> clubs = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_clubs);


        recyclerView = findViewById(R.id.clubRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        clubAdapter = new ClubAdapter(new ArrayList<>(), this); // Pass a non-null list here

        recyclerView.setAdapter(clubAdapter);

        clubAdapter.setOnItemClickListener(new ClubAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
             //   if (clubs != null && position < clubs.size()) {
                //    Intent intent = new Intent(ListeClubs.this, ModifierEnseignant.class);
//                    startActivity(intent);
            //    } else {
                    // Log or show a message indicating that the list is null or the position is invalid
             //   }
            }
        });


        recyclerView.setAdapter(clubAdapter);

        loadClubs();
    }

    private void loadClubs() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                ClubDao clubDao = MyDatabase.getInstance(ListeClubs.this).clubDao();

                final List<Club> clubs = clubDao.getAllClubs();


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        clubAdapter.setClubs(clubs);
                    }
                });
            }
        }).start();
    }


}