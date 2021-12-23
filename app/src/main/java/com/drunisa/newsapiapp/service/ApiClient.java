package com.drunisa.newsapiapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit mRetrofit = null;

    public static Retrofit getClient(String url) {

        mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return mRetrofit;
    }
}
