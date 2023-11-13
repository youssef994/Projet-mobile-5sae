package com.example.myapplication.Entite;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "Etudiants")
public class Etudiant extends User {

    @ColumnInfo(name = "niveau")
    public String niveau;


    @ColumnInfo(name = "classe_id")
    public long classeId; // Clé étrangère

    @Ignore
    public Etudiant() {
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public long getClasseId() {
        return classeId;
    }

    public void setClasseId(long classeId) {
        this.classeId = classeId;
    }

    public Etudiant(String niveau, long classeId) {
        super();
        this.niveau = niveau;
        this.classeId = classeId;
    }


}
