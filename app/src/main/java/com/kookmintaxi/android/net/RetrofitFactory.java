package com.kookmintaxi.android.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JaewookAhn on 15/02/2017.
 */

public class RetrofitFactory {


    public static final String ENDPOINT = "http://52.78.77.212";
    private static RetrofitFactory instance;

    private Retrofit retrofit;

    private RetrofitFactory() {
        instance = this;
        retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ENDPOINT)
                .build();
    }

    public static RetrofitFactory getInstance() {
        return instance != null ? instance : new RetrofitFactory();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
