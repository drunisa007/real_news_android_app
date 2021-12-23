package com.drunisa.newsapiapp.model;

import com.google.gson.annotations.SerializedName;

public class BlogModel {

    private String id;
    private String author;
    @SerializedName("download_url")
    private String url;

    public BlogModel(String id, String author, String url) {
        this.id = id;
        this.author = author;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }



}
