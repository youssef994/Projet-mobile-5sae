package com.example.myapplication.Entite;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Clubs")
public class Club {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "nom")
    public String nom;

    @ColumnInfo(name = "nbr_p")
    public String nbr_p;

    @ColumnInfo(name = "description")
    public String description;

    @Ignore
    public Club() {
        // Constructeur sans arguments annoté avec @Ignore
        // Room ignorera ce constructeur lors de la création de la base de données
    }


    public Club(Long id,String nom,String nbr_p, String description) {
        this.id = id;
        this.nom = nom;
        this.nbr_p = nbr_p;
        this.description = description;
    }

}
