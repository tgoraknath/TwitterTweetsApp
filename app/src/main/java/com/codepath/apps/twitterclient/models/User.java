package com.codepath.apps.twitterclient.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gtulla on 6/3/16.
 */
public class User {
    private String name;
    private long uId;
    private String screeName;
    private String profileImageUrl;
    public User() {
    }

    public String getName() {
        return name;
    }

    public long getuId() {
        return uId;
    }

    public String getScreeName() {
        return screeName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
    public static User buildUser(JSONObject jsonObj) {
        User u = new User();
        try {
            u.name = jsonObj.getString("name");
            u.uId = jsonObj.getLong("id");
            u.screeName = jsonObj.getString("screen_name");
            u.profileImageUrl = jsonObj.getString("profile_image_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return u;
    }
}
