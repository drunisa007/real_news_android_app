package com.drunisa.newsapiapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ArticleRecyclerAdapter extends RecyclerView.Adapter<ArticleRecyclerAdapter.MyViewHolder>{


    ArrayList<ArticleModel> mArticleList;

    Context mContext;

    ArticleRecyclerAdapter(MainActivity mainActivity,ArrayList<ArticleModel> mArticleList){
        this.mContext = mainActivity;
        this.mArticleList = mArticleList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aricle_single_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        ArticleModel mArticleData = mArticleList.get(position);

        holder.mArticleTitle.setText(mArticleData.getTitle());
        holder.mArticleDesc.setText(mArticleData.getDescription());

        Glide.with(mContext).load(mArticleData.getUrlToImage()).into(holder.mArticleImage);

    }

    @Override
    public int getItemCount() {
        return mArticleList.size();
    }

    protected static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView mArticleImage;
        TextView mArticleTitle,mArticleDesc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mArticleImage = itemView.findViewById(R.id.article_single_image);
            mArticleTitle = itemView.findViewById(R.id.article_single_title);
            mArticleDesc = itemView.findViewById(R.id.article_single_desc);
        }
    }
}
