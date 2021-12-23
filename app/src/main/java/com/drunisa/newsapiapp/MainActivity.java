package com.drunisa.newsapiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {



    RecyclerView mArticleRecyclerView;

    ArrayList<ArticleModel> mArticleList = new ArrayList<ArticleModel>();

    ArticleRecyclerAdapter mArticleRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gettingAllView();

        settingRecyclerView();

        NewsApi mNewsApi = ApiClient.getClient().create(NewsApi.class);
        Call<NewsModel> newsModelCall = mNewsApi.getNews();
        newsModelCall.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call,Response<NewsModel> response) {

                if(response.isSuccessful()){
                    mArticleList.addAll(response.body().getArticleModel());
                    mArticleRecyclerAdapter = new ArticleRecyclerAdapter(MainActivity.this,mArticleList);
                    mArticleRecyclerView.setAdapter(mArticleRecyclerAdapter);
                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Log.i("MainActivity","error fetching on data"+t);
            }
        });


    }

    private void settingRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        mArticleRecyclerView.setLayoutManager(linearLayoutManager);

         mArticleRecyclerAdapter = new ArticleRecyclerAdapter(MainActivity.this,mArticleList);

        mArticleRecyclerView.setAdapter(mArticleRecyclerAdapter);
    }

    private void gettingAllView() {
        mArticleRecyclerView = findViewById(R.id.article_recyclerview);
    }
}