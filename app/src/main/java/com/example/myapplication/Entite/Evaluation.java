package com.example.myapplication.Entite;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Evaluations")
public class Evaluation {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "noteExamen")
    public String noteExamen;

    @ColumnInfo(name = "noteCc")
    public String noteCc;

    @ColumnInfo(name = "remarque")
    public String remarque;

    @Ignore
    public Evaluation() {
        // Constructeur sans arguments annoté avec @Ignore
        // Room ignorera ce constructeur lors de la création de la base de données
    }


    public Evaluation(Long id,String noteExamen,String noteCc, String remarque) {
        this.id = id;
        this.noteExamen = noteExamen;
        this.noteCc = noteCc;
        this.remarque = remarque;

    }

}
