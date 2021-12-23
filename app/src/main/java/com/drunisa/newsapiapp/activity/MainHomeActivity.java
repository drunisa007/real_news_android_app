package com.drunisa.newsapiapp.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.drunisa.newsapiapp.R;
import com.drunisa.newsapiapp.db.AppDatabase;
import com.drunisa.newsapiapp.db.BlogDao;
import com.drunisa.newsapiapp.db.BlogEntity;
import com.drunisa.newsapiapp.fragment.ArticleFragment;
import com.drunisa.newsapiapp.fragment.BlogFragment;
import com.drunisa.newsapiapp.util.InternetConnection;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class MainHomeActivity extends AppCompatActivity {

    BottomNavigationView mNavigationView;

    Toolbar mToolbar;

    final Fragment articleFragment = new ArticleFragment();
    final Fragment blogFragment = new BlogFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = articleFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        if(!InternetConnection.isNetworkAvailable(getApplicationContext())){
          Snackbar snackbar=Snackbar.make(findViewById(R.id.main_frame), "NO INTERNET CONNECTION", Snackbar.LENGTH_SHORT)
                    .setAction("REFRESH", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    })
                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ));

          snackbar.show();

        }


//        BlogDao dao = AppDatabase.getDbInstance(getApplicationContext()).blogDao();
//
//        List<BlogEntity> mList =dao.getAllBlogs();
//
//        dao.insertBlog(new BlogEntity("1","Alejandro Escamilla","https://picsum.photos/id/0/5616/3744"));
//        dao.delete();
//
//        Log.d("MainHomeActivity",mList.size()+"");

        mNavigationView = findViewById(R.id.main_nav);
        mToolbar = findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("News");

        Log.d("MainHomeActivity", InternetConnection.isNetworkAvailable(getApplicationContext())+"");

        fm.beginTransaction().add(R.id.main_frame, blogFragment, "2").hide(blogFragment).commit();
        fm.beginTransaction().add(R.id.main_frame, articleFragment, "1").commit();


        mNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.nav_home:
                    fm.beginTransaction().hide(active).show(articleFragment).commit();
                    active = articleFragment;
                    getSupportActionBar().setTitle("News");
                    return true;

                case R.id.nav_addblogs:
                    fm.beginTransaction().hide(active).show(blogFragment).commit();
                    getSupportActionBar().setTitle("Photos");
                    active = blogFragment;
                    return true;
            }
            return false;
        });
    }
}