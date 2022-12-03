package com.example.proyectopvzgw2;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import java.util.List;

public class recyclerAlfabeticFragment extends RecyclerCharacterFragment {
    LiveData<List<Character>> obtenerElementos() {
        return characterViewModel.alfabetico();
    }
}
