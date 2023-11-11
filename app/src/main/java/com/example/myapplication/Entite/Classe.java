package com.example.myapplication.Entite;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Classes")
public class Classe {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "nom")
    public String nom;

    @ColumnInfo(name = "specialite")
    public String specialite;


    @ColumnInfo(name = "numero")
    public String numero;

    @Ignore
    public Classe() {
        // Constructeur sans arguments annoté avec @Ignore
        // Room ignorera ce constructeur lors de la création de la base de données
    }


    public Classe(Long id,String nom,String specialite, String numero) {
        this.id = id;
        this.nom = nom;
        this.specialite = specialite;
        this.numero = numero;

    }

}
