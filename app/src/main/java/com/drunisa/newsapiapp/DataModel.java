package com.drunisa.newsapiapp;

import com.google.gson.annotations.SerializedName;



//testing
public class DataModel {

    private  int userId ;

    private int id;

    private String title;

    @SerializedName("completed")
    private Boolean completed;


    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getCompleted() {
        return completed;
    }

}
