package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codepath.apps.restclienttemplate.MyDatabase;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

import java.util.ArrayList;
import java.util.List;


//@Table(database = MyDatabase.class)
public class Tweet extends BaseModel {
//    // Define database columns and associated fields
//    @PrimaryKey @Column
//    Long id;
//    @Column
//    String userId;
//    @Column
//    String userHandle;
//    @Column
//    String timestamp;
//    @Column
//    String body;
//
//
//    // Add a constructor that creates an object from the JSON response
//    public Tweet(JSONObject object){
//        super();
//
//        try {
//            this.userId = object.getString("user_id");
//            this.userHandle = object.getString("user_username");
//            this.timestamp = object.getString("timestamp");
//            this.body = object.getString("body");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ArrayList<Tweet> fromJson(JSONArray jsonArray) {
//        ArrayList<Tweet> tweets = new ArrayList<Tweet>(jsonArray.length());
//
//        for (int i=0; i < jsonArray.length(); i++) {
//            JSONObject tweetJson = null;
//            try {
//                tweetJson = jsonArray.getJSONObject(i);
//            } catch (Exception e) {
//                e.printStackTrace();
//                continue;
//            }
//
//            Tweet tweet = new Tweet(tweetJson);
//            tweet.save();
//            tweets.add(tweet);
//        }
//
//        return tweets;
//    }
    @SerializedName("id")
    private long id;

    @SerializedName("text")
    private String text;

    @SerializedName("created_at")
    private String TimeStamp;

    @SerializedName("user")
    private User user;

    @SerializedName("extended_entities")
    private Extend extend;

    public Extend getExtend() {
        return extend;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }
}