package com.example.android.quakereport;

/**
 * Created by Admin on 12-Mar-17.
 */

public class Quake {
    private String mMagnitude;
    private String mPlace;
    private String mDate;

    public Quake(String Mag, String Place, String Date){
        mMagnitude = Mag;
        mPlace = Place;
        mDate = Date;
    }
    public String GetMag (){
        return mMagnitude;
    }
    public String GetPlace (){
        return mPlace;
    }
    public String GetDate (){
        return mDate;
    }

}
