package com.drunisa.newsapiapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.drunisa.newsapiapp.db.ArticleEntity;
import com.drunisa.newsapiapp.db.BlogEntity;
import com.drunisa.newsapiapp.model.ArticleModel;
import com.drunisa.newsapiapp.R;
import com.drunisa.newsapiapp.activity.ArticleSingleActivity;

import java.util.ArrayList;

public class ArticleRecyclerAdapter extends RecyclerView.Adapter<ArticleRecyclerAdapter.MyViewHolder>{


    ArrayList<ArticleModel> mArticleList;
    ArrayList<ArticleEntity> mArticleDbList = new ArrayList<>();

    Context mContext;

    public ArticleRecyclerAdapter(Context mainActivity, ArrayList<ArticleModel> mArticleList,ArrayList<ArticleEntity> mArticleDbList){
        this.mContext = mainActivity;
        this.mArticleList = mArticleList;
        this.mArticleDbList = mArticleDbList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aricle_single_recycler_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        if(mArticleList.size()==0){

            ArticleEntity mArticleData = mArticleDbList.get(position);

            holder.mArticleTitle.setText(mArticleData.getTitle());
            holder.mArticleDesc.setText(mArticleData.getDescription());

            Glide.with(mContext)
                    .load(mArticleData.getUrlToImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.mArticleImage);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(mContext, ArticleSingleActivity.class);
                    intent.putExtra("article",mArticleData);
                    mContext.startActivity(intent);
                }
            });
        }
        else{
            ArticleModel mArticleData = mArticleList.get(position);

            holder.mArticleTitle.setText(mArticleData.getTitle());
            holder.mArticleDesc.setText(mArticleData.getDescription());

            Glide.with(mContext)
                    .load(mArticleData.getUrlToImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.mArticleImage);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(mContext, ArticleSingleActivity.class);
                    intent.putExtra("article",mArticleData);
                    mContext.startActivity(intent);
                }
            });
        }



    }

    @Override
    public int getItemCount() {

        if(mArticleList.size()==0){
            return mArticleDbList.size();
        }

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
