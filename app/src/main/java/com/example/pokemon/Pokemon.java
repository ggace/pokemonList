package com.example.pokemon;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pokemon {
    private int id;
    private String name;

    public String getImgUrl() {
        return "https://pokeres.bastionbot.org/images/pokemon/" + id + ".png";
    }

    @JsonCreator
    public Pokemon(@JsonProperty("name") String name, @JsonProperty("url") String url){
        this.name = name;
        String[] urlBits = url.split("/");

        this.id = Integer.parseInt(urlBits[urlBits.length - 1]);
    }
}
