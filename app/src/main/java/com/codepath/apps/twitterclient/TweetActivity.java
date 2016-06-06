package com.codepath.apps.twitterclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class TweetActivity extends AppCompatActivity {// implements View.OnClickListener {
    private TwitterClient client = null;
    private EditText etTweet = null;
    private TextView tvCnt = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);
        client = TwitterRestApplication.getRestClient();
        etTweet = (EditText)findViewById(R.id.etTweetMsg);
        tvCnt = (TextView)findViewById(R.id.tvCnt);
        etTweet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tvCnt.setText("140");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvCnt.setText(""+(140-s.length()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        tvCnt.refreshDrawableState();
        setupViewItems();
    }

    private void setupViewItems() {
    }

    /**
    @Override
    public void onClick(View v) {

    }*/

    public void tweetMsg(View view) {
        //invoke Tweet API to post the msg..
        //on success navigate user to timeline activity
        //on failure, toast a failure message and stay on same page.
       // etTweet = (EditText)view.findViewById(R.id.etTweetMsg);
        String tweet = etTweet.getText().toString();
        client.postTweet(tweet, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("DEBUG", response.toString());
                //take user to TimelineActivity
                homepage();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("DEBUG", responseString);
            }
        });
    }

    private void homepage() {
        Intent i = new Intent(this, TimelineActivity.class);
        startActivity(i);
    }
}
