package com.example.pokemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static int nextOffset = 0;
    static int limit = 20;

    static Integer maxCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        final RecyclerView recyclerViewPokemon = findViewById(R.id.activity_main__recyclerViewPokemon);


        PokemonService pokemonService = new PokemonService();


        PokemonAdapter pokemonAdapter = new PokemonAdapter();



        
        pokemonAdapter.setBtnFooterClicked(v -> {

            pokemonService.getPokemon(nextOffset, limit, responseBody -> {
                for(int i = 0; i < responseBody.getResults().size(); i++){
                    pokemonAdapter.addPokemon(responseBody.getResults().get(i));

                }
                nextOffset += limit;

                pokemonAdapter.notifyDataSetChanged();
            });
        });
        
        recyclerViewPokemon.setAdapter(pokemonAdapter);
        
        

/*
        pokemonService.getPokemon(0, 50, responseBody -> {
            recyclerViewPokemon.setAdapter(new PokemonAdapter(responseBody.getResults()));
        });*/


    }
}