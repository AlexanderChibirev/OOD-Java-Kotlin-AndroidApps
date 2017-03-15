package com.example.alexander.testapplication.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.alexander.testapplication.R;
import com.example.alexander.testapplication.model.FeedItem;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class AppController {

    private static final String FIELD_NAME_MODEL = "mRssChannelUrl";

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

    String getRssUrl() {
        return mSharedPreferences.getString(
                mContext.getString(R.string.preference_address_rss_key), null);
    }

    public ArrayList<FeedItem> getFeedItems() {
        return mFeedItems;
    }

    void setFeedItems(ArrayList<FeedItem> feedItems) {
        mFeedItems = feedItems;
    }

    public boolean isChangeUrl() {
        String newUrl = getRssUrl();
        if (newUrl == null || newUrl.equals(mCurrentRssUrl)) {
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

    void UpdateDataFromDB() {
        PutTheDataInDB();
        RealmResults<FeedItem> feedItemsDB = mRealmDB.where(FeedItem.class)//sql search for channel
                .beginsWith(FIELD_NAME_MODEL, getRssUrl()) //mRssChannelUrl = field name
                .findAll();

        ArrayList<FeedItem> feedItems = new ArrayList<>();
        for (int i = 0; i < feedItemsDB.size() - 1; i++) {
            FeedItem feedItem = new FeedItem();
            feedItem.setRssChannelUrl(feedItemsDB.get(i).getRssChannelUrl());
            feedItem.setFeedID(feedItemsDB.get(i).getFeedID());
            feedItem.setThumbnailUrl(feedItemsDB.get(i).getThumbnailUrl());
            feedItem.setAuthor(feedItemsDB.get(i).getAuthor());
            feedItem.setDescription(feedItemsDB.get(i).getDescription());
            feedItem.setPubDate(feedItemsDB.get(i).getPubDate());
            feedItem.setTitle(feedItemsDB.get(i).getTitle());
            feedItem.setLink(feedItemsDB.get(i).getLink());
            feedItems.add(feedItem);
        }
        setFeedItems(feedItems);
    }

    private void PutTheDataInDB() {
        for (final FeedItem feedItem : getFeedItems()) {
            mRealmDB.executeTransaction(realm -> mRealmDB.copyToRealmOrUpdate(feedItem));
        }
    }

    private void initRealmDB(Context context) {
        Realm.init(context);
        mRealmDB = Realm.getDefaultInstance();
    }
}