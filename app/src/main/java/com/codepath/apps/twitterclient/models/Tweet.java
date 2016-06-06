package com.codepath.apps.twitterclient.models;

import com.codepath.apps.twitterclient.util.ParseRelativeDate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gtulla on 6/3/16.
 */
public class Tweet {
    private String body;
    private long id;
    private String createdDt;
    private User user;
    public Tweet() {
    }

    public String getBody() {
        return body;
    }

    public long getId() {
        return id;
    }

    public String getCreatedDt() {
        return createdDt;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "body='" + body + '\'' +
                ", id=" + id +
                ", createdDt='" + createdDt + '\'' +
                ", user=" + user +
                '}';
    }

    public static Tweet buildTweet(JSONObject jsonObj) throws JSONException {
        Tweet t = new Tweet();
        t.body = jsonObj.getString("text");
        t.id =  jsonObj.getLong("id");
        t.createdDt = ParseRelativeDate.getRelativeTimeAgo(jsonObj.getString("created_at"));
        JSONObject userObj = jsonObj.getJSONObject("user");
        t.user = User.buildUser(userObj);
        return t;
    }

    public static List<Tweet> buildTweets(JSONArray jsonArray) {
        List<Tweet> tweets = new ArrayList<>();
        Tweet tweet = null;
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                tweet = buildTweet(jsonArray.getJSONObject(i));
                if(tweet != null) {
                    tweets.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }
       return tweets;
    }
}
