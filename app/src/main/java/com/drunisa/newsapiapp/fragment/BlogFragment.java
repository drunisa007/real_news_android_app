package com.drunisa.newsapiapp.fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.drunisa.newsapiapp.R;
import com.drunisa.newsapiapp.adapter.MyBlogRecyclerAdapter;
import com.drunisa.newsapiapp.db.AppDatabase;
import com.drunisa.newsapiapp.db.BlogDao;
import com.drunisa.newsapiapp.db.BlogEntity;
import com.drunisa.newsapiapp.model.BlogModel;
import com.drunisa.newsapiapp.service.ApiClient;
import com.drunisa.newsapiapp.service.BlogsApi;
import com.drunisa.newsapiapp.util.InternetConnection;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BlogFragment extends Fragment {


    public BlogFragment() {
        // Required empty public constructor
    }
    
    
    RecyclerView mBlogRecyclerView;
    NestedScrollView mBlogScroll;
    ProgressBar mBlogProgressBar;

    int page = 1;
    int limit = 10;

    ArrayList<BlogModel> mBlogList = new ArrayList<>();
    ArrayList<BlogEntity> mBlogDbList = new ArrayList<>();

    BlogDao blogDao;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_blog, container, false);

        blogDao = AppDatabase.getDbInstance(view.getContext()).blogDao();

        getAllView(view);

        settingUpRecyclerView(view);

        if(InternetConnection.isNetworkAvailable(view.getContext())){
            fetchingBlogsData(view,true);
        }
        else{
            List<BlogEntity> mList =blogDao.getAllBlogs();
            mBlogDbList.addAll(mList);
            mBlogRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            mBlogRecyclerView.setAdapter(new MyBlogRecyclerAdapter(view.getContext(),mBlogList,mBlogDbList));
        }

        workingOnBlogScroll(view);

        return view;
    }

    private void workingOnBlogScroll(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mBlogScroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if(scrollY==v.getChildAt(0).getMeasuredHeight()-v.getMeasuredHeight()){
                        if(InternetConnection.isNetworkAvailable(view.getContext())){
                            mBlogProgressBar.setVisibility(View.VISIBLE);
                            page++;
                            fetchingBlogsData(view,false);
                        }
                    }
                }
            });
        }
    }

    private void settingUpRecyclerView(View view) {
        mBlogRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mBlogRecyclerView.setAdapter(new MyBlogRecyclerAdapter(view.getContext(),mBlogList,mBlogDbList));

    }

    private void fetchingBlogsData(View view,Boolean isFirstTime) {

        if(isFirstTime){
            blogDao.delete();
        }

        BlogsApi blogsApi = ApiClient.getClient("https://picsum.photos/").create(BlogsApi.class);
        Call<List<BlogModel>> mCallBlogs = blogsApi.getBlogsList(page,limit);

        mCallBlogs.enqueue(new Callback<List<BlogModel>>() {
            @Override
            public void onResponse(Call<List<BlogModel>> call, Response<List<BlogModel>> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    mBlogProgressBar.setVisibility(View.INVISIBLE);
                    mBlogList.addAll(response.body());

                    for(int i=0;i<response.body().size();i++){
                        BlogModel mModel = response.body().get(i);
                        blogDao.insertBlog(new BlogEntity(mModel.getId(),mModel.getAuthor(),mModel.getUrl()));
                    }
                    mBlogRecyclerView.setAdapter(new MyBlogRecyclerAdapter(view.getContext(),mBlogList,mBlogDbList));
                }
            }

            @Override
            public void onFailure(Call<List<BlogModel>> call, Throwable t) {

            }
        });
    }

    private void getAllView(View view) {
        mBlogRecyclerView = view.findViewById(R.id.blog_recyclerview);
        mBlogScroll = view.findViewById(R.id.blog_scroll);
        mBlogProgressBar = view.findViewById(R.id.blog_progress_bar);
    }
}