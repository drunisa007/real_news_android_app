package com.drunisa.newsapiapp;


import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApi {


   @GET("v2/everything?q=headline&apiKey="+Constant.APIKEY)
   Call<NewsModel>  getNews();

}
