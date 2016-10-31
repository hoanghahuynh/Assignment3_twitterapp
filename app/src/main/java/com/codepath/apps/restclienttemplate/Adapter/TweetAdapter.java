package com.codepath.apps.restclienttemplate.Adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HoangHa on 10/28/2016.
 */

public class TweetAdapter extends RecyclerView.Adapter {
    private final int NORMAL = 0;
    private final int NO_IMAGE = 1;
    List<Tweet> mTweets;
    private Listener mListener;

    public interface Listener {
        void OnLoadMore();
    }

    public void setListener(Listener listener){
        mListener = listener;
    }

    public TweetAdapter() {
        this.mTweets = new ArrayList<>();
    }
    public void setmTweets(List<Tweet> tweets) {
        mTweets.clear();
        mTweets.addAll(tweets);
        notifyDataSetChanged();
    }
    public void addmTweets(List<Tweet> tweets){
        int startPosition = mTweets.size();
        mTweets.addAll(tweets);
        notifyItemRangeInserted(startPosition,tweets.size());
    }

    public void addFirstTweet(Tweet tweet) {
        mTweets.add(0,tweet);
        notifyItemInserted(0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tweet,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Tweet tweet = mTweets.get(position);
        bindNormal(tweet,(ViewHolder)holder);
        if((position == mTweets.size()-1) && mListener != null)
            mListener.OnLoadMore();
    }

    private void bindNormal(Tweet tweet, ViewHolder holder) {
        holder.tvPost.setText(tweet.getText());
        holder.tvUser.setText(tweet.getUser().getName());
        holder.tvTimeStamp.setText(tweet.getTimeStamp());
        Picasso.with(holder.itemView.getContext())
                .load(tweet.getUser().getProfile_image_url())
                .into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    //ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivImage)
        ImageView ivImage;
        @BindView(R.id.tvUser)
        TextView tvUser;
        @BindView(R.id.tvPost)
        TextView tvPost;
        @BindView(R.id.tvTimeStamp)
        TextView tvTimeStamp;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
