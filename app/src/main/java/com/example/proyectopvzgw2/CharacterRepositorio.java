package com.example.proyectopvzgw2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CharacterRepositorio {

    CharacterBaseDeDatos.ElementosDao elementosDao;
    Executor executor = Executors.newSingleThreadExecutor();

    CharacterRepositorio(Application application){
        elementosDao = CharacterBaseDeDatos.obtenerInstancia(application).obtenerElementosDao();
    }


    LiveData<List<Character>> obtener(){
        return elementosDao.obtener();
    }

    LiveData<List<Character>> alfabetico() {
        return elementosDao.alfabetico();
    }


    void insertar(Character character){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                elementosDao.insertar(character);
            }
        });
    }

    void eliminar(Character character) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                elementosDao.eliminar(character);
            }
        });
    }


}
