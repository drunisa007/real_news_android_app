package com.drunisa.newsapiapp.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ArticleDao {

    @Query("SELECT * FROM ArticleEntity")
    List<ArticleEntity> getAllArticle();

    @Insert
    void insertArticle(ArticleEntity... entity);

    @Query("DELETE FROM ArticleEntity")
    void delete();
}
