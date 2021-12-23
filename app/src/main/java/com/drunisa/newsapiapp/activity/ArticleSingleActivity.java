package com.drunisa.newsapiapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.drunisa.newsapiapp.adapter.ArticleRecyclerAdapter;
import com.drunisa.newsapiapp.model.ArticleModel;
import com.drunisa.newsapiapp.R;
import com.drunisa.newsapiapp.model.NewsModel;
import com.drunisa.newsapiapp.service.ApiClient;
import com.drunisa.newsapiapp.service.NewsApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleSingleActivity extends AppCompatActivity {




    ImageView mArticleImage;
    TextView mArticleTitle,mArticleDesc;

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_single_layout);





        gettingAllView();

        settingToolbar();

        ArticleModel mModel = (ArticleModel) getIntent().getSerializableExtra("article");

        getSupportActionBar().setTitle(mModel.getTitle());


        Glide.with(ArticleSingleActivity.this).load(mModel.getUrlToImage()).error(R.drawable.placeholder).into(mArticleImage);
        mArticleTitle.setText(mModel.getTitle());
        mArticleDesc.setText(mModel.getDescription());
    }

    private void settingToolbar() {
    setSupportActionBar(mToolbar);
    mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onBackPressed();
        }
    });
    }

    private void gettingAllView() {
        mArticleImage = findViewById(R.id.article_image);
        mArticleTitle = findViewById(R.id.article_title);
        mArticleDesc = findViewById(R.id.article_desc);

        mToolbar = findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}