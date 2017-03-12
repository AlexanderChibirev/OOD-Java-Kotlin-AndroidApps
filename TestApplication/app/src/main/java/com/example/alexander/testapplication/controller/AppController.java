package com.example.alexander.testapplication.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.alexander.testapplication.R;
import com.example.alexander.testapplication.model.FeedItem;

import java.util.ArrayList;

import io.realm.Realm;

public class AppController {
    private SharedPreferences mSharedPreferences;
    private Context mContext;
    private String mCurrentRssUrl;
    private ArrayList<FeedItem> mFeedItems = new ArrayList<>();
    private Realm mRealmDB;

    public AppController(Context context) {
        initRealmDB(context);
        mContext = context;
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mCurrentRssUrl = getRssUrl();
    }

    public String getRssUrl() {
        return mSharedPreferences.getString(
                mContext.getString(R.string.preference_address_rss_key), null);
    }

    private void initRealmDB(Context context) {
        Realm.init(context);
        mRealmDB = Realm.getDefaultInstance();
    }

    public ArrayList<FeedItem> getFeedItems() {
        return mFeedItems;
    }

    public boolean isChangeUrl() {
        String newUrl = getRssUrl();
        if (newUrl.equals(mCurrentRssUrl)) {
            return false;
        } else {
            mCurrentRssUrl = newUrl;
            return true;
        }
    }

    public boolean isUrlEmpty() {
        return mCurrentRssUrl == null;
    }

    public Realm getRealmDB() {
        return mRealmDB;
    }
}