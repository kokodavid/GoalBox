package com.david.goalbox;

public class Leagues {

    private String mName;
    private String mCountry;
    private String mSeason;
    private String mStart;
    private String mImageUrl;
    private String mEnd;


    public Leagues(String name, String mCountry, String mSeason,
                       String imageUrl,
                      String mStart, String mEnd) {
        this.mName = name;
        this.mCountry = mCountry;
        this.mSeason = mSeason;
        this.mImageUrl = imageUrl;
        this.mStart = mStart;
        this.mEnd = mEnd;

    }

    public String getName() {
        return mName;
    }

    public String getCountry() {
        return mCountry;
    }

    public String getSeason() {
        return  mSeason;
    }

    public String getImageUrl() {
        return mStart;
    }


    public String Start() {
        return mEnd;
    }

}

