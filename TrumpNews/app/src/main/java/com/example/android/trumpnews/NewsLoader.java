package com.example.android.trumpnews;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

/**
 * Created by Admin on 12-Apr-17.
 */

public class NewsLoader extends AsyncTaskLoader<List<TrumpTweets>> {

    private static final String LOG_TAG = NewsLoader.class.getName();
    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG,"TEST: onStartLoading() called ...");
        forceLoad();
    }

    @Override
    public List<TrumpTweets> loadInBackground() {
        Log.i(LOG_TAG,"TEST: loadInBackground() called ...");
        if (mUrl == null) {
            return null;
        }

        List<TrumpTweets> trumpTweetsList = NewsUtils.fetchTrumpData(mUrl);

        return trumpTweetsList;
    }
}
