package com.drunisa.newsapiapp.service;

import com.drunisa.newsapiapp.model.BlogModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BlogsApi {

    @GET("v2/list")
    Call<List<BlogModel>> getBlogsList(
            @Query("page") int page,
            @Query("limit") int limit
    );
}
