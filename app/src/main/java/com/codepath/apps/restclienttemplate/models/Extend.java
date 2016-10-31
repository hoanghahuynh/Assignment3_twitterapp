package com.codepath.apps.restclienttemplate.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HoangHa on 10/28/2016.
 */
public class Extend {
    @SerializedName("media")
    private List<TweetMedia> medias;

    public List<TweetMedia> getMedia() {
        return medias;
    }
}
