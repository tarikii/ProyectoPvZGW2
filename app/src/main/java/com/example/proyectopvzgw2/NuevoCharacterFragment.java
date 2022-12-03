package com.example.proyectopvzgw2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.proyectopvzgw2.databinding.FragmentNuevoCharacterBinding;

public class NuevoCharacterFragment extends Fragment {
    private FragmentNuevoCharacterBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentNuevoCharacterBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CharacterViewModel characterViewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
        NavController navController = Navigation.findNavController(view);

        binding.create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.name.getText().toString();
                String description = binding.description.getText().toString();

                switch (name){
                    case "pomelo":
                        characterViewModel.insertar(new Character(R.drawable.pomelo,name, description));
                        break;
                    case "engineer":
                        characterViewModel.insertar(new Character(R.drawable.engineer,name, description));
                        break;
                    default:
                        characterViewModel.insertar(new Character(R.drawable.sunflower,name, description));
                        break;
                }
                navController.popBackStack();
            }
        });
    }
}