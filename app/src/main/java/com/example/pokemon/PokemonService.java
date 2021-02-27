package com.example.pokemon;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.NonNull;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class PokemonService {

    private PokeApi pokeApi;

    public PokemonService(){
        OkHttpClient okHttpClient = null;
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        /*if (BuildConfig.DEBUG) {
            // Stetho Interceptor 추가해야 Chrome Inspect tool 에서 확인 가능, 필수 아님
            okHttpClientBuilder.addNetworkInterceptor(new StethoInterceptor());
        }*/

        okHttpClient = okHttpClientBuilder.build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create()) // GSON 사용
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()); // RX Java 사용

        retrofitBuilder.client(okHttpClient);

        Retrofit retrofit = retrofitBuilder
                .baseUrl(PokeApi.baseUrl)
                .build();

        pokeApi = retrofit.create(PokeApi.class);
    }

    public void getPokemon(int offset, int limit, @NonNull Consumer<? super PokeApi__getPokemons__ResponseBody> onNext) {
        pokeApi.getPokemons(offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, throwable -> {
                    Util.log("throwable : " + throwable.getMessage());
                });
    }
}
