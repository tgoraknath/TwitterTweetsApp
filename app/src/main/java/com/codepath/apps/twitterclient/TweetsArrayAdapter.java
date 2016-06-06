package com.codepath.apps.twitterclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.twitterclient.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by gtulla on 6/3/16.
 */
public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {
    public TweetsArrayAdapter(Context context, List<Tweet> tweets) {
        super(context, android.R.layout.simple_list_item_1, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the tweet
        //find and inflate the layour
        //find the sub views
        //ViewHolder pattern
        Tweet tweet = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);
        }
        TextView name = (TextView)convertView.findViewById(R.id.tvName);
        TextView userName = (TextView)convertView.findViewById(R.id.tvScreenName);
        TextView body = (TextView)convertView.findViewById(R.id.tvBoby);
        TextView rdate = (TextView)convertView.findViewById(R.id.tvRDate);
        ImageView profileImg = (ImageView)convertView.findViewById(R.id.ivProfileImg);
        name.setText(tweet.getUser().getName());
        userName.setText(tweet.getUser().getScreeName());
        body.setText(tweet.getBody());
        rdate.setText(tweet.getCreatedDt());
        profileImg.setImageResource(android.R.color.transparent);
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(profileImg);
        return convertView;
    }
}
