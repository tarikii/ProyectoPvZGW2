package com.example.proyectopvzgw2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class CharacterViewModel extends AndroidViewModel {

    CharacterRepositorio characterRepositorio;


    MutableLiveData<Character> elementoSeleccionado = new MutableLiveData<>();


    public CharacterViewModel(@NonNull Application application) {
        super(application);

        characterRepositorio = new CharacterRepositorio(application);
    }



    LiveData<List<Character>> obtener(){
        return characterRepositorio.obtener();
    }

    LiveData<List<Character>> alfabetico(){
        return characterRepositorio.alfabetico();
    }

    void insertar(Character character){
        characterRepositorio.insertar(character);
    }

    void eliminar(Character character){
        characterRepositorio.eliminar(character);
    }

    void seleccionar(Character character){
        elementoSeleccionado.setValue(character);
    }

    MutableLiveData<Character> seleccionado(){
        return elementoSeleccionado;
    }
}
