package com.example.proyectopvzgw2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

import javax.security.auth.callback.Callback;

@Database(entities = {Character.class}, version = 1, exportSchema = false)
public abstract class CharacterBaseDeDatos extends RoomDatabase {

    public abstract ElementosDao obtenerElementosDao();

    private static volatile CharacterBaseDeDatos INSTANCIA;

    static CharacterBaseDeDatos obtenerInstancia(final Context context) {
        if (INSTANCIA == null) {
            synchronized (CharacterBaseDeDatos.class) {
                if (INSTANCIA == null) {
                    INSTANCIA = Room.databaseBuilder(context,
                                    CharacterBaseDeDatos.class, "characters.db")
                            .fallbackToDestructiveMigration()
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);

                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCIA;
    }

    @Dao
    interface ElementosDao {
        @Query("SELECT * FROM Character")
        LiveData<List<Character>> obtener();

        @Insert
        void insertar(Character character);

        @Update
        void actualizar(Character character);

        @Delete
        void eliminar(Character character);

        @Query("SELECT * FROM Character ORDER BY name")
        LiveData<List<Character>> alfabetico();

    }
}
