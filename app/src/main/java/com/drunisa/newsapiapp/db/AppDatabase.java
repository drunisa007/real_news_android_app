package com.drunisa.newsapiapp.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {BlogEntity.class,ArticleEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BlogDao blogDao();
    public abstract ArticleDao articleDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context) {
        if (INSTANCE == null) {
             INSTANCE = Room.
                     databaseBuilder(context.getApplicationContext(),AppDatabase.class,"news")
                     .allowMainThreadQueries()
                     .build();
        }
        return INSTANCE;
    }
}
