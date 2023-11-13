package com.example.myapplication.Entite;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.Interface.EnseignantDao;
import com.example.myapplication.Interface.EvaluationDao;

@Database(entities = {Enseignant.class, Evaluation.class}, version =2)
public abstract class MyDatabase extends RoomDatabase {
    public abstract EnseignantDao enseignantDao();
    public abstract EvaluationDao evaluationDao();

    private static MyDatabase instance;

    public static synchronized MyDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "my_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
