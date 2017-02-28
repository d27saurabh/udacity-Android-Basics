package com.example.android.miwok;

/**
 * Created by Admin on 13-Jan-17.
 */

public class Word {

    private String mText1;
    private String mText2;
    private int mDrawableId;
    private boolean hasIMG;
    private int mSoundFile;

    public Word(String Text1, String Text2, int Sound) {
        mText1 = Text1;
        mText2 = Text2;
        hasIMG = false;
        mSoundFile = Sound;
    }

    public Word(String Text1, String Text2, int drawableId, int Sound) {
        mText1 = Text1;
        mText2 = Text2;
        mDrawableId = drawableId;
        hasIMG = true;
        mSoundFile = Sound;
    }

    public String getDefaultTranslation() {
        return mText1;
    }

    public String getMiwokTranslation() {
        return mText2;
    }

    public int getIMGrsrcId() {
        return mDrawableId;
    }

    public boolean hasImage() {
        return hasIMG;
    }

    public int getSoundFile () {
        return mSoundFile;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mText1='" + mText1 + '\'' +
                ", mText2='" + mText2 + '\'' +
                ", mDrawableId=" + mDrawableId +
                ", hasIMG=" + hasIMG +
                ", mSoundFile=" + mSoundFile +
                '}';
    }
}
