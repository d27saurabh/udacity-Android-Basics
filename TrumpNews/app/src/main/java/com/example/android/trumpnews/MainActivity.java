package com.example.android.trumpnews;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<TrumpTweets>> {

    private static final String LOG_TAG = MainActivity.class.getName();
    private NewsAdapter adapter;
    private View progressBar;
    boolean isConnected;
    TextView emptyView;
    private static final String TRUMP_REQUEST_URL = "http://content.guardianapis.com/search?q=trump&api-key=34db90f0-de3d-4c04-9e56-77557a355090";
    private static final int NEWS_LOADER_ID = 1;

    public void CheckInternetIsConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckInternetIsConnected(this);

        Log.i(LOG_TAG, "TEST: MainActivity activity onCreate() called");

        ArrayList<TrumpTweets> trumpTweets = new ArrayList<>();
        trumpTweets.add(new TrumpTweets("Nuked north korea", "Politics", "10/10/10", "wwww.com"));
        trumpTweets.add(new TrumpTweets("Built a WAll", "Politics", "10/10/10", "wwww.com"));
        trumpTweets.add(new TrumpTweets("Nuked north korea", "Politics", "10/10/10", "wwww.com"));

        ListView newsListView = (ListView) findViewById(R.id.list);
        emptyView = (TextView) findViewById(R.id.empty_view);
        newsListView.setEmptyView(emptyView);
        adapter = new NewsAdapter(this, new ArrayList<TrumpTweets>());
        newsListView.setAdapter(adapter);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TrumpTweets trumpURL = adapter.getItem(position);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(trumpURL.getURL()));
                startActivity(intent);
            }
        });

        if (isConnected) {
            LoaderManager loaderManager = getLoaderManager();
            Log.i(LOG_TAG, "TEST: initLoader() calling ...");

            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            progressBar = findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.GONE);

            // Set empty state text to display "No internet..."
            emptyView.setText(R.string.no_internet);
        }
    }

    @Override
    public Loader<List<TrumpTweets>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG, "TEST: onCreateLoader() called ...");
        return new NewsLoader(this, TRUMP_REQUEST_URL);
    }


    @Override
    public void onLoadFinished(Loader<List<TrumpTweets>> loader, List<TrumpTweets> data) {
        Log.i(LOG_TAG, "TEST: onLoadFinished() called ...");
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);

        // Set empty state text to display "No earthquakes found."
        emptyView.setText(R.string.no_news);
        // Clear the adapter of previous earthquake data
        adapter.clear();

        if (data != null && !data.isEmpty()) {
            adapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<TrumpTweets>> loader) {
        Log.i(LOG_TAG, "TEST: onLoaderRESET() called ...");

        // Loader reset, so we can clear out our existing data.
        adapter.clear();
    }
}
