package com.example.android.trumpnews;

/**
 * Created by Admin on 12-Apr-17.
 */

public class TrumpTweets {

    private String mTitle;
    private String mSectionName;
    private String mPublicationDate;
    private String mURL;

    public  TrumpTweets (String Title, String SectionName, String Date, String URL){
        mTitle = Title;
        mSectionName = SectionName;
        mPublicationDate = Date;
        mURL = URL;
    }
    public String getTitle(){
        return mTitle;
    }
    public String getSectionName(){
        return mSectionName;
    }
    public String getPublicationDate(){
        return mPublicationDate;
    }
    public String getURL(){
        return mURL;
    }
}
