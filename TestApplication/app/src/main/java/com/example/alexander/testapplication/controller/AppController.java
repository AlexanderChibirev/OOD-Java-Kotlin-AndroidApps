package com.example.alexander.testapplication.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.alexander.testapplication.R;

public class AppController {
    private SharedPreferences mSharedPreferences;
    private Context mContext;
    private String mCurrentRssUrl = "";

    public AppController(Context context) {
        mContext = context;
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mCurrentRssUrl = getRssUrl();

    }

    public void updateRssData() {
        ReadRss readRss = new ReadRss(mCurrentRssUrl);
        readRss.execute();
    }

    public String getRssUrl() {
        return mSharedPreferences.getString(
                mContext.getString(R.string.preference_address_rss_key), null);
    }

    public boolean isCorrectRssUrl() {
        return android.util.Patterns.WEB_URL.matcher(getRssUrl()).matches();
    }

    public boolean canUpdateRV() {//TODO:: add tests
        String newUrl = getRssUrl();
        if (mCurrentRssUrl.equals(newUrl)) {
            return false;
        } else if (isCorrectRssUrl() && !mCurrentRssUrl.equals(newUrl)) {
            mCurrentRssUrl = newUrl;
            return true;
        }
        return false;
    }
}
