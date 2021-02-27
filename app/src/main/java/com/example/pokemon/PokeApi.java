package com.example.pokemon;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeApi {
    String baseUrl = "https://pokeapi.co/api/v2/";

    @GET("pokemon")
    Observable<PokeApi__getPokemons__ResponseBody> getPokemons(@Query("offset") int offset, @Query("limit") int limit);
}
