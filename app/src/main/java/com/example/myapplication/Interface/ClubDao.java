package com.example.myapplication.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.Entite.Club;
import com.example.myapplication.Entite.Enseignant;

import java.util.List;

@Dao
public interface ClubDao {

    @Insert
    void insert(Club club);

    @Update
    void update(Club club);

    @Delete
    void delete(Club club);

    @Query("SELECT * FROM Club")
    List<Club> getAllClubs();
    @Query("SELECT * FROM Club WHERE id = :id")
    Club getClubById(long id);
}
