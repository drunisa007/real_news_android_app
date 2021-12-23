package com.drunisa.newsapiapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.drunisa.newsapiapp.R;
import com.drunisa.newsapiapp.db.BlogEntity;
import com.drunisa.newsapiapp.model.BlogModel;

import java.util.ArrayList;

public class MyBlogRecyclerAdapter extends RecyclerView.Adapter<MyBlogRecyclerAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<BlogModel> mBlogList = new ArrayList<>();
    ArrayList<BlogEntity> mBlogDbList = new ArrayList<>();

    public MyBlogRecyclerAdapter(Context context, ArrayList<BlogModel> mBlogList,ArrayList<BlogEntity> mBlogDbList) {
         this.mContext = context;
         this.mBlogList = mBlogList;
         this.mBlogDbList = mBlogDbList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_reycler_single_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if(mBlogList.size()==0){
            BlogEntity mBlogData = mBlogDbList.get(position);

            Glide.with(mContext)
                    .load(mBlogData.getUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.mBlogImage);

            holder.mBlogTitle.setText(mBlogData.getAuthor());
        }
        else{
            BlogModel mBlogData = mBlogList.get(position);

            Glide.with(mContext)
                    .load(mBlogData.getUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.mBlogImage);

            holder.mBlogTitle.setText(mBlogData.getAuthor());
        }

    }

    @Override
    public int getItemCount() {

        if(mBlogList.size()==0){
            return mBlogDbList.size();
        }

        return mBlogList.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView mBlogImage;
        TextView mBlogTitle;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mBlogImage = itemView.findViewById(R.id.blog_single_image);
            mBlogTitle = itemView.findViewById(R.id.blogs_single_title);
        }
    }
}
