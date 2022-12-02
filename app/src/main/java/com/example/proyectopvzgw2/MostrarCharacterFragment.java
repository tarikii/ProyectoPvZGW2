package com.example.proyectopvzgw2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyectopvzgw2.databinding.FragmentMostrarCharacterBinding;

public class MostrarCharacterFragment extends Fragment {
    private FragmentMostrarCharacterBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentMostrarCharacterBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CharacterViewModel characterViewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);

        characterViewModel.seleccionado().observe(getViewLifecycleOwner(), new Observer<Character>() {
            @Override
            public void onChanged(Character character) {
                binding.name.setText(character.name);
                binding.description.setText(character.description);
                binding.image.setImageResource(character.image);

            }
        });
    }
}