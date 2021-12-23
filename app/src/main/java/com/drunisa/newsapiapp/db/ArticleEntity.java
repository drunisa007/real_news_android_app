package com.drunisa.newsapiapp.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class ArticleEntity implements Serializable {
    public ArticleEntity(String title, String description, String urlToImage) {
        this.title = title;
        this.description = description;
        this.urlToImage = urlToImage;
    }

    @PrimaryKey(autoGenerate = true)
    public int uid;
    public String title;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String description;
    public String urlToImage;
}
