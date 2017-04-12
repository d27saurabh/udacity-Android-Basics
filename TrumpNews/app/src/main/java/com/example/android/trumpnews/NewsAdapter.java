package com.example.android.trumpnews;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 12-Apr-17.
 */

public class NewsAdapter extends ArrayAdapter<TrumpTweets> {

    public NewsAdapter(Activity activity, ArrayList<TrumpTweets> news) {
        super(activity, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View tweet = convertView;
        if (tweet == null){
            tweet = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
        }

        TrumpTweets trumpTweets = getItem(position);

        TextView title = (TextView) tweet.findViewById(R.id.news_title);
        TextView section = (TextView) tweet.findViewById(R.id.section_name);
        TextView date = (TextView) tweet.findViewById(R.id.news_date);

        title.setText(trumpTweets.getTitle());
        section.setText(trumpTweets.getSectionName());
        date.setText(trumpTweets.getPublicationDate());

        return tweet;
    }
}
