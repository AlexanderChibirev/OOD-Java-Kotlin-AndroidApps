package com.example.alexander.testapplication.model;


import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.example.alexander.testapplication.model.dataBase.FeedContentProvider;


public class FeedItem implements Parcelable {

    @NonNull
    private String mTitle = "";
    @NonNull
    private String mLink = "";
    @NonNull
    private String mDescription = "";
    @NonNull
    private String mPubDate = "";
    @NonNull
    private String mThumbnailUrl = "";
    @NonNull
    private String mAuthor = "";

    private int mRssChannelID;
    private int mFeedID;


    public static final Creator<FeedItem> CREATOR = new Creator<FeedItem>() {
        @Override
        public FeedItem createFromParcel(Parcel in) {
            return new FeedItem(in);
        }

        @Override
        public FeedItem[] newArray(int size) {
            return new FeedItem[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mLink);
        parcel.writeString(mDescription);
        parcel.writeString(mPubDate);
        parcel.writeString(mThumbnailUrl);
        parcel.writeString(mAuthor);
        parcel.writeInt(mRssChannelID);
        parcel.writeInt(mFeedID);
    }


    public FeedItem() {

    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getPubDate() {
        return mPubDate;
    }

    public void setPubDate(String pubDate) {
        mPubDate = pubDate;
    }

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        mThumbnailUrl = thumbnailUrl;
    }


    private FeedItem(Parcel in) {
        mTitle = in.readString();
        mLink = in.readString();
        mDescription = in.readString();
        mPubDate = in.readString();
        mThumbnailUrl = in.readString();
        mAuthor = in.readString();
        mRssChannelID = in.readInt();
        mFeedID = in.readInt();
    }

    public ContentValues getContentValuesForFeed() {
        ContentValues cv = new ContentValues();
        cv.put(FeedContentProvider.FEED_TITLE, mTitle);
        cv.put(FeedContentProvider.FEED_LINK, mLink);
        cv.put(FeedContentProvider.FEED_AUTHOR, mAuthor);
        cv.put(FeedContentProvider.FEED_DESCRIPTION, mDescription);
        cv.put(FeedContentProvider.FEED_PUB_DATE, mPubDate);
        cv.put(FeedContentProvider.FEED_THUMBNAIL_URL, mThumbnailUrl);
        return cv;
    }

    public ContentValues getContentValuesForBinding() {
        ContentValues cv = new ContentValues();
        cv.put(FeedContentProvider.FEED_RSS_CHANNEL_FEED_ID, mFeedID);
        cv.put(FeedContentProvider.FEED_RSS_CHANNEL_CHANNEL_ID, mRssChannelID);
        return cv;
    }

    public void setFeedID(int feedID) {
        mFeedID = feedID;
    }

    public void setChannelID(int rssChannelID) {
        mRssChannelID = rssChannelID;
    }
}
