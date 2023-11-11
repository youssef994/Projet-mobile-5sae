package com.example.myapplication.Entite;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Evenements")
public class Evenement {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "datt")
    public String datt;

    @ColumnInfo(name = "description")
    public String description;

    @Ignore
    public Evenement() {
        // Constructeur sans arguments annoté avec @Ignore
        // Room ignorera ce constructeur lors de la création de la base de données
    }


    public Evenement(Long id,String datt,String description) {
        this.id = id;
        this.datt = datt;
        this.description = description;
    }

}
