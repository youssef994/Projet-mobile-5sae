package com.example.myapplication.Entite;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myapplication.Interface.ClasseDao;
import com.example.myapplication.Interface.EnseignantDao;
import com.example.myapplication.Interface.EtudiantDao;
import com.example.myapplication.Interface.EvaluationDao;


@Database(entities = {Enseignant.class, Evaluation.class, Classe.class, Etudiant.class}, version = 3)

public abstract class MyDatabase extends RoomDatabase {
    public abstract EnseignantDao enseignantDao();
    public abstract EvaluationDao evaluationDao();

    public abstract ClasseDao classeDao();
    public abstract EtudiantDao etudiantDao();
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



    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new RemplissageInitialAsyncTask(instance).execute();
        }
    };



    private static class RemplissageInitialAsyncTask extends AsyncTask<Void, Void, Void> {
        private ClasseDao classeDao;
        private EtudiantDao etudiantDao;

        private RemplissageInitialAsyncTask(MyDatabase db) {
            classeDao = db.classeDao();
            etudiantDao = db.etudiantDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Insérez vos données statiques ici
            Classe c1 = new Classe();
            c1.setId(1);
            c1.setNom("5SAE5");
            c1.setSpecialite("SAE");
            c1.setNumero("5");

            Classe c2 = new Classe();
            c2.setId(2);
            c2.setNom("5TWIN4");
            c2.setSpecialite("TWIN");
            c2.setNumero("4");

            classeDao.insertStatique(c1, c2); // Mettez à jour ici aussi

            return null;
        }
    }


}
