package com.example.myapplication.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.Entite.Etudiant;


import java.util.List;

@Dao
public interface EtudiantDao {

    @Insert
    void insert(Etudiant etudiant);

    @Update
    void update(Etudiant etudiant);

    @Delete
    void delete(Etudiant etudiant);

    @Query("SELECT * FROM Etudiants")
    List<Etudiant> getAllEtudiants();

}


