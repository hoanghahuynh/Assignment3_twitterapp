package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class PostTweet extends AppCompatActivity {

    @BindView(R.id.edtTweetContent)
    EditText edtTweetContent;
    @BindView(R.id.btnCompose)
    Button btnCompose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_tweet);

        ButterKnife.bind(this);

        btnCompose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestApplication.getRestClient().postTweet(edtTweetContent.getText().toString(), new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Intent i = new Intent();
                        Toast.makeText(PostTweet.this,"post success: ", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK, i);
                        finish();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(PostTweet.this,"post fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
