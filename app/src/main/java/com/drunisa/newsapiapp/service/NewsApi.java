package com.drunisa.newsapiapp.service;


import com.drunisa.newsapiapp.model.NewsModel;
import com.drunisa.newsapiapp.util.Constant;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApi {


   @GET("v2/everything?q=headline&apiKey="+ Constant.APIKEY)
   Call<NewsModel>  getNews();

}
