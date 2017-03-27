package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by Admin on 27-Mar-17.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Quake>> {

    private static final String LOG_TAG = EarthquakeLoader.class.getName();
    private String mUrl;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG,"TEST: onStartLoading() called ...");
        forceLoad();
    }

    @Override
    public List<Quake> loadInBackground() {
        Log.i(LOG_TAG,"TEST: loadInBackground() called ...");
        if (mUrl == null) {
            return null;
        }

        List<Quake> quakesList = QueryUtils.fetchEarthquakeData(mUrl);
        return quakesList;
    }
}
