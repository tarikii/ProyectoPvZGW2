package com.example.proyectopvzgw2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectopvzgw2.databinding.FragmentRecyclerCharacterBinding;
import com.example.proyectopvzgw2.databinding.ViewholderCharacterBinding;

import java.util.List;

public class RecyclerCharacterFragment extends Fragment {

    private FragmentRecyclerCharacterBinding binding;
    CharacterViewModel characterViewModel;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentRecyclerCharacterBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        characterViewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
        navController = Navigation.findNavController(view);

        binding.irANuevoCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_nuevoCharacterFragment);
            }
        });

        ElementosAdapter elementosAdapter;
        elementosAdapter = new ElementosAdapter();

        binding.recyclerView.setAdapter(elementosAdapter);

        binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT  | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int posicion = viewHolder.getAdapterPosition();
                Character character = elementosAdapter.obtenerElemento(posicion);
                characterViewModel.eliminar(character);

            }
        }).attachToRecyclerView(binding.recyclerView);

        obtenerElementos().observe(getViewLifecycleOwner(), new Observer<List<Character>>() {
            @Override
            public void onChanged(List<Character> Characters) {
                elementosAdapter.establecerLista(Characters);
            }
        });
    }

    LiveData<List<Character>> obtenerElementos(){
        return characterViewModel.obtener();
    }

    class ElementosAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

        List<Character> Characters;

        @NonNull
        @Override
        public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CharacterViewHolder(ViewholderCharacterBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {

            Character character = Characters.get(position);

            holder.binding.name.setText(character.name);

            Glide.with(RecyclerCharacterFragment.this).load(character.image).into(holder.binding.image);


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    characterViewModel.seleccionar(character);
                    navController.navigate(R.id.action_mostrarCharacterFragment);
                }
            });
        }

        @Override
        public int getItemCount() {
            return Characters != null ? Characters.size() : 0;
        }

        public void establecerLista(List<Character> Champions){
            this.Characters = Characters;
            notifyDataSetChanged();
        }

        public Character obtenerElemento(int posicion){
            return Characters.get(posicion);
        }
    }

    static class CharacterViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderCharacterBinding binding;

        public CharacterViewHolder(ViewholderCharacterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}