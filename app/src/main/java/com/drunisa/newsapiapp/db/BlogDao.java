package com.drunisa.newsapiapp.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BlogDao {
    @Query("SELECT * FROM BLOGENTITY")
    List<BlogEntity> getAllBlogs();

    @Insert
    void insertBlog(BlogEntity... entity);

    @Query("DELETE FROM BLOGENTITY")
    void delete();
}
