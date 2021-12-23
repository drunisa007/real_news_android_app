package com.drunisa.newsapiapp.db;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BlogEntity {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    public int getUid() {
        return uid;
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

    @ColumnInfo(name = "id")
    public String id;

    @ColumnInfo(name = "author")
    public String author;
    @ColumnInfo(name = "url")
    public String url;


    public BlogEntity(String id, String author, String url) {
        this.id = id;
        this.author = author;
        this.url = url;
    }



}
