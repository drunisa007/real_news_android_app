package com.drunisa.newsapiapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {


    @GET("todos/1")
    Call<DataModel> getData();
}
