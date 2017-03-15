package com.example.alexander.testapplication.model;


import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class FeedItem extends RealmObject implements Parcelable {
    //когда базу данных добавлю, нужно будет убрать Parcelable
    //и уже не нужно будет передавать в активити сложный объект
    //передавать нужно будет стринг mFeedID и в превьюшке просто будем обращаться из базы данных к обх

    @PrimaryKey
    private String mFeedID;

    private String mTitle;
    private String mLink;
    private String mDescription;
    private String mPubDate;
    private String mAuthor;
    private String mThumbnailUrl;
    private String mRssChannelUrl;

    public FeedItem() {

    }

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
        parcel.writeString(mRssChannelUrl);
        parcel.writeString(mFeedID);
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
        mRssChannelUrl = in.readString();
        mFeedID = in.readString();
    }

    public String getRssChannelUrl() {
        return mRssChannelUrl;
    }

    public void setRssChannelUrl(String rssChannelUrl) {
        mRssChannelUrl = rssChannelUrl;
    }

    public String getFeedID() {
        return mFeedID;
    }

    public void setFeedID(String feedID) {
        mFeedID = feedID;
    }
}
