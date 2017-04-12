package com.example.android.trumpnews;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 12-Apr-17.
 */

public final class NewsUtils {
    private static final String LOG_TAG = NewsUtils.class.getName();

    private NewsUtils() {

    }

    public static List<TrumpTweets> fetchTrumpData(String requestUrl) {
        Log.i(LOG_TAG, "TEST: fetchTrumpData() called ...");

        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create an {@link Event} object
        List<TrumpTweets> trumpNews = extractNewsFromJSON(jsonResponse);

        // Return the {@link Event}
        return trumpNews;
    }

    private static URL createUrl(String requestUrl) {
        URL url = null;
        try {
            url = new URL(requestUrl);
        } catch (MalformedURLException e) {
            // failed
            Log.e(LOG_TAG, "Problem building the URL ", e);
            return null;
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If url is null return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + httpURLConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the news JSON results.", e);
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStream != null) {

                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder jsonBuilder = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                jsonBuilder.append(line);
                line = reader.readLine();
            }
        }
        return jsonBuilder.toString();
    }

    private static List<TrumpTweets> extractNewsFromJSON(String newsJSON) {

        // Create an empty ArrayList that we can start adding news to
        List<TrumpTweets> trumpNewzzz = new ArrayList<>();

        // parse JSON
        try {

            JSONObject reader = new JSONObject(newsJSON);
            JSONArray news = reader.getJSONArray("results");

            for (int i = 0; i < news.length(); i++) {
                JSONObject Data = news.getJSONObject(i);

                String title = Data.getString("webTitle");
                String sectionName = Data.getString("sectionName");
                String date = Data.getString("webPublicationDate");
                String url = Data.getString("webUrl");

                TrumpTweets currentNews = new TrumpTweets(title, sectionName, date, url);
                trumpNewzzz.add(currentNews);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e(LOG_TAG, "Problem parsing the news JSON results", e);
        }

        // Return the list of news
        return trumpNewzzz;
    }
}
