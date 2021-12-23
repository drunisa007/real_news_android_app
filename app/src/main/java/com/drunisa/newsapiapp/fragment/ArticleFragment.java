package com.drunisa.newsapiapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.drunisa.newsapiapp.R;
import com.drunisa.newsapiapp.adapter.ArticleRecyclerAdapter;
import com.drunisa.newsapiapp.adapter.MyBlogRecyclerAdapter;
import com.drunisa.newsapiapp.db.AppDatabase;
import com.drunisa.newsapiapp.db.ArticleDao;
import com.drunisa.newsapiapp.db.ArticleEntity;
import com.drunisa.newsapiapp.db.BlogDao;
import com.drunisa.newsapiapp.db.BlogEntity;
import com.drunisa.newsapiapp.model.ArticleModel;
import com.drunisa.newsapiapp.model.BlogModel;
import com.drunisa.newsapiapp.model.NewsModel;
import com.drunisa.newsapiapp.service.ApiClient;
import com.drunisa.newsapiapp.service.NewsApi;
import com.drunisa.newsapiapp.util.InternetConnection;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ArticleFragment extends Fragment {



    public ArticleFragment() {
        // Required empty public constructor
    }

    RecyclerView mArticleRecyclerView;

    ProgressBar mArticleProgressbar;

    ArrayList<ArticleModel> mArticleList = new ArrayList<ArticleModel>();
    ArrayList<ArticleEntity> mArticleDbList = new ArrayList<>();

    ArticleDao articleDao;

    ArticleRecyclerAdapter mArticleRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        articleDao = AppDatabase.getDbInstance(view.getContext()).articleDao();
        gettingAllView(view);


        settingRecyclerView(view);



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(InternetConnection.isNetworkAvailable(view.getContext())){
            fetchingArticleData(view);
        }
        else{
            List<ArticleEntity> mList =articleDao.getAllArticle();
            mArticleDbList.addAll(mList);
            mArticleProgressbar.setVisibility(View.INVISIBLE);
            mArticleRecyclerView.setVisibility(View.VISIBLE);
            mArticleRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            mArticleRecyclerView.setAdapter(new ArticleRecyclerAdapter(view.getContext(),mArticleList,mArticleDbList));
        }

    }

    private void fetchingArticleData(View view) {

        articleDao.delete();

        NewsApi mNewsApi = ApiClient.getClient("https://newsapi.org/").create(NewsApi.class);

        Call<NewsModel> newsModelCall = mNewsApi.getNews();
        newsModelCall.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {

                if(response.isSuccessful()&&response.body()!=null){
                    mArticleProgressbar.setVisibility(View.INVISIBLE);
                    mArticleRecyclerView.setVisibility(View.VISIBLE);
                    mArticleList.addAll(response.body().getArticleModel());

                    for(int i=0;i<response.body().getArticleModel().size();i++){
                        ArticleModel mModel =response.body().getArticleModel().get(i);
                        articleDao.insertArticle(new ArticleEntity(mModel.getTitle(),mModel.getDescription(),mModel.getUrlToImage()));
                    }

                    mArticleRecyclerAdapter = new ArticleRecyclerAdapter(view.getContext(),mArticleList,mArticleDbList);
                    mArticleRecyclerView.setAdapter(mArticleRecyclerAdapter);

                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Log.i("MainActivity","error fetching on data"+t);
            }
        });
    }

    private void settingRecyclerView(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        mArticleRecyclerView.setLayoutManager(linearLayoutManager);

        mArticleRecyclerAdapter = new ArticleRecyclerAdapter(view.getContext(),mArticleList,mArticleDbList);

        mArticleRecyclerView.setAdapter(mArticleRecyclerAdapter);
    }

    private void gettingAllView(View view) {
        mArticleRecyclerView = view.findViewById(R.id.article_recyclerview);
        mArticleProgressbar = view.findViewById(R.id.article_progress_bar);

    }

}