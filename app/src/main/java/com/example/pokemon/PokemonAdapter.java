package com.example.pokemon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Pokemon> data;



    int TYPE_HEADER = 0;
    int TYPE_ITEM = 1;
    int TYPE_FOOTER = 2;

    View.OnClickListener btnFooterClicked;

    public PokemonAdapter(List<Pokemon> data){
        this.data = data;
    }

    public void addPokemon(Pokemon pokemon){
        data.add(pokemon);
    }

    public PokemonAdapter() {
        this.data = new ArrayList<>();
    }

    public void setBtnFooterClicked(View.OnClickListener btnFooterClicked){
        this.btnFooterClicked = btnFooterClicked;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == TYPE_FOOTER){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.pokemon_footer, parent, false);

            return new FooterViewHolder(view);
        }
        else if(viewType == TYPE_HEADER){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.pokemon_header, parent, false);

            return new HeaderViewHolder(view);
        }
        else{
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.pokemon_item, parent, false);

            return new ItemViewHolder(view);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof HeaderViewHolder){

        }
        else if(holder instanceof FooterViewHolder){
            FooterViewHolder footerViewHolder = (FooterViewHolder)holder;

            footerViewHolder.footer.setOnClickListener(btnFooterClicked);
        }
        else if(holder instanceof ItemViewHolder){

            int pokemonIndex = position - 1;

            ItemViewHolder itemViewHolder = (ItemViewHolder)holder;

            Pokemon pokemon = data.get(pokemonIndex);



            itemViewHolder.textViewId.setText(pokemon.getId() + "번");
            itemViewHolder.textViewId.setTag(pokemonIndex);

            itemViewHolder.textViewName.setText(pokemon.getName());
            itemViewHolder.textViewName.setTag(pokemonIndex);

            Util.loadImageOn(pokemon.getImgUrl(), itemViewHolder.imageViewPokemon);

            String imgUrl = "https://pokeres.bastionbot.org/images/pokemon/" + pokemon.getId() + ".png";

            //Util.log(imgUrl);

            Util.loadImageOn(imgUrl, itemViewHolder.imageViewPokemon);
        }


    }

    @Override
    public int getItemCount() {
        return data.size() + 2;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewId;
        public TextView textViewName;
        public ImageView imageViewPokemon;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewId = itemView.findViewById(R.id.item_pokemon__textViewId);
            textViewName = itemView.findViewById(R.id.item_pokemon__textViewName);
            imageViewPokemon = itemView.findViewById(R.id.item_pokemon__imageViewPokemon);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder{
        TextView header;
        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);

            header = itemView.findViewById(R.id.header);
        }
    }
    public static class FooterViewHolder extends RecyclerView.ViewHolder{
        Button footer;
        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);

            footer = itemView.findViewById(R.id.footer);
        }


    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_HEADER;
        }
        else if(position == data.size()+1){
            return TYPE_FOOTER;
        }
        else{
            return TYPE_ITEM;
        }
    }
}
