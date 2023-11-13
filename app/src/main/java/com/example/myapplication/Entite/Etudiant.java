package com.example.myapplication.Entite;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "Etudiants")
public class Etudiant extends User {

    @ColumnInfo(name = "niveau")
    public String niveau;


    @Ignore
    public Etudiant() {
    }


    public Etudiant(String niveau) {
        super();
        this.niveau = niveau;
    }

}
