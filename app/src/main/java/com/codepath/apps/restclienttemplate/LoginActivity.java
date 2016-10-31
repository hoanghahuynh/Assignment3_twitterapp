package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.oauth.OAuthLoginActionBarActivity;
import com.codepath.oauth.OAuthLoginActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends OAuthLoginActionBarActivity<RestClient> {
	private Gson mGson;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		mGson = new Gson();
	}


	// Inflate the menu; this adds items to the action bar if it is present.
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	// OAuth authenticated successfully, launch primary authenticated activity
	// i.e Display application "homepage"
	static int count = 0;
	@Override
	public void onLoginSuccess() {
		Toast.makeText(this,"success: " + Integer.valueOf(count++),Toast.LENGTH_SHORT).show();
		Intent i = new Intent(LoginActivity.this, HomeTimeLine.class);
		startActivity(i);
		/*RestApplication.getRestClient().getHomeTimeline(1, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				super.onSuccess(statusCode, headers, response);
				Toast.makeText(LoginActivity.this,"Object",Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
				super.onSuccess(statusCode, headers, response);
				List<Tweet> tweets = mGson.fromJson(
						response.toString(),new TypeToken<List<Tweet>>() {}.getType());

				ImageView ivLoadedImg = (ImageView) findViewById(R.id.ivLoadedImg);
				String imageUri = tweets.get(1).getExtend().getMedia().get(0).getMediaUrl();
				Picasso.with(getApplicationContext())
						.load(imageUri)
						.into(ivLoadedImg);

				Toast.makeText(LoginActivity.this,"Array",Toast.LENGTH_SHORT).show();

			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				super.onSuccess(statusCode, headers, responseString);
				Toast.makeText(LoginActivity.this,"String",Toast.LENGTH_SHORT).show();
			}

		});*/
	}

	// OAuth authentication flow failed, handle the error
	// i.e Display an error dialog or toast
	@Override
	public void onLoginFailure(Exception e) {
		e.printStackTrace();
	}

	// Click handler method for the button used to start OAuth flow
	// Uses the client to initiate OAuth authorization
	// This should be tied to a button used to login
	public void loginToRest(View view) {
		getClient().connect();
	}

}
