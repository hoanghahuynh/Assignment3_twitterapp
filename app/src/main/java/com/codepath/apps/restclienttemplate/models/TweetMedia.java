package com.codepath.apps.restclienttemplate.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HoangHa on 10/28/2016.
 */
public class TweetMedia {
    @SerializedName("media_url_https")
    private String mediaUrl;

    public String getMediaUrl() {
        return mediaUrl;
    }
}
