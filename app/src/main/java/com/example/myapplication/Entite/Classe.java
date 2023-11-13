package com.example.myapplication.Entite;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

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


    // Liste d'étudiants dans cette classe
    @Ignore
    public List<Etudiant> etudiants;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }
}
