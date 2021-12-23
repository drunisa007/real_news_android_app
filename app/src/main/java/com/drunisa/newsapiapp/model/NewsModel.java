package com.drunisa.newsapiapp.model;

import com.drunisa.newsapiapp.model.ArticleModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsModel {

    private int totalResult;
    @SerializedName("articles")
    private List<ArticleModel> articleModel;

    public int getTotalResult() {
        return totalResult;
    }

    public List<ArticleModel> getArticleModel() {
        return articleModel;
    }




    public NewsModel(int totalResult, List<ArticleModel> articleModel) {
        this.totalResult = totalResult;
        this.articleModel = articleModel;
    }


}


