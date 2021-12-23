package com.drunisa.newsapiapp.model;

import java.io.Serializable;

public class ArticleModel implements Serializable {

    private String title;
    private String description;
    private String urlToImage;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }


}
