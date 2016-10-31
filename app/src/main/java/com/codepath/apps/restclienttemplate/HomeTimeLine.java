package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ProgressBar;
import android.view.View;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.Adapter.TweetAdapter;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class HomeTimeLine extends AppCompatActivity {

    private TweetAdapter mTweetAdapter;
    private StaggeredGridLayoutManager mLayoutManager;
    private Gson mGson;

    private static int page=1;

    @BindView(R.id.rvTweet)
    RecyclerView rvTweet;
    @BindView(R.id.pdLoading)
    View pdLoading;
    @BindView(R.id.pdLoadMore)
    ProgressBar pdLoadMore;

    private interface Listener {
        void onResult(List<Tweet> tweets);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_time_line);

        mGson = new Gson();
        ButterKnife.bind(this);

        mTweetAdapter = new TweetAdapter();
        mTweetAdapter.setListener(new TweetAdapter.Listener() {

            @Override
            public void OnLoadMore() {
                loadMore(false);
            }
        });
    // ham
        mLayoutManager = new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL);
        rvTweet.setLayoutManager(mLayoutManager);
        rvTweet.setAdapter(mTweetAdapter);


//        rvTweet.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//
//                return true;
//            }
//
//            @Override
//            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });


        load();
    }

    private void load(){
        page = 1;
        pdLoading.setVisibility(View.VISIBLE);
        fetchTweet(new Listener() {
            @Override
            public void onResult(List<Tweet> tweets) {
                mTweetAdapter.setmTweets(tweets);
            }
        });
    }

    private void loadMore(boolean refresh){
        if (refresh) page--;
        page += 1;
        pdLoadMore.setVisibility(View.VISIBLE);
        fetchTweet(new Listener() {
            @Override
            public void onResult(List<Tweet> tweets) {
                mTweetAdapter.addmTweets(tweets);
            }
        });
    }

    private void loadNewPost() {
        page=1;
        pdLoading.setVisibility(View.VISIBLE);
        fetchTweet(new Listener() {
            @Override
            public void onResult(List<Tweet> tweets) {
                mTweetAdapter.addFirstTweet(tweets.get(0));
            }
        });
        Toast.makeText(HomeTimeLine.this,"You have a new Tweet.",Toast.LENGTH_SHORT).show();
    }

    private void fetchTweet(final Listener listener) {
        RestApplication.getRestClient().getHomeTimeline(page, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                List<Tweet> tweets = mGson.fromJson(
                        response.toString(),new TypeToken<List<Tweet>>() {}.getType());
                //mTweetAdapter.setmTweets(tweets);
                listener.onResult(tweets);

                handleComplete();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                //super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e("Error-ops:", throwable.getMessage());
            }
        });
    }

    private void handleComplete() {
        pdLoading.setVisibility(View.GONE);
        pdLoadMore.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.actionbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_reload:
                loadMore(true);
                break;
            case R.id.action_tweet:
                Intent i = new Intent(this,PostTweet.class);
                startActivityForResult(i,13);

                break;
            default:
                Toast.makeText(this,"can't happen here",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 13)
            loadNewPost();

    }
}
