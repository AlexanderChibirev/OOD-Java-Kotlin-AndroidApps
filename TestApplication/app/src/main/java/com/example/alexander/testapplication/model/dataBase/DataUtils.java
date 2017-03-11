package com.example.alexander.testapplication.model.dataBase;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;

import com.example.alexander.testapplication.model.FeedItem;

public class DataUtils {

    private static ContentResolver mContentResolver;

    public static void loadContentResolver(ContentResolver cv) {
        mContentResolver = cv;
    }

    private static Cursor getAllFeedIDsByChannel(String channel) {
        return mContentResolver.query(
                FeedContentProvider.FEEDS_RSS_CHANNELS_CONTENT_URI,
                new String[]{FeedContentProvider.FEED_RSS_CHANNEL_FEED_ID},
                FeedContentProvider.FEED_RSS_CHANNEL_CHANNEL_ID + " = ? ",
                new String[]{String.valueOf(getChannelID(channel))},
                null
        );
    }

    // Only for channels methods
    public static Cursor getAllChannels() {
        return mContentResolver.query(FeedContentProvider.RSS_CHANNELS_CONTENT_URI,
                null, null, null, null);
    }

    public static boolean checkChannelExists(String channel) {
        Cursor cursor = mContentResolver.query(
                FeedContentProvider.RSS_CHANNELS_CONTENT_URI,
                null,
                FeedContentProvider.RSS_CHANNEL_TITLE + " = ? ",
                new String[]{channel},
                null);
        if (cursor == null) return false;
        int cnt = cursor.getCount();
        cursor.close();
        return cnt != 0;
    }

    public static void addChannel(String channel) {
        ContentValues cv = new ContentValues();
        cv.put(FeedContentProvider.RSS_CHANNEL_TITLE, channel);
        mContentResolver.insert(FeedContentProvider.RSS_CHANNELS_CONTENT_URI, cv);
    }

    public static int getChannelID(String channel) {
        Cursor cursor = mContentResolver.query(
                FeedContentProvider.RSS_CHANNELS_CONTENT_URI,
                new String[]{FeedContentProvider.RSS_CHANNEL_ID},
                FeedContentProvider.RSS_CHANNEL_TITLE + " = ? ",
                new String[]{channel},
                null
        );
        if (cursor == null) return -1;
        cursor.moveToPosition(0);
        int channelID = cursor.getInt(cursor.getColumnIndexOrThrow(FeedContentProvider.RSS_CHANNEL_ID));
        cursor.close();
        return channelID;
    }

    public static void deleteChannel(String channel) {

    }

    // Only for feeds methods
    public static void addFeed(FeedItem feedItem) {
        ContentValues cv = feedItem.getContentValuesForFeed();
        mContentResolver.insert(FeedContentProvider.FEEDS_CONTENT_URI, cv);
        int feedID = getFeedID(feedItem);
        feedItem.setFeedID(feedID);
        cv = feedItem.getContentValuesForBinding();
        mContentResolver.insert(FeedContentProvider.FEEDS_RSS_CHANNELS_CONTENT_URI, cv);
    }

    private static int getFeedID(FeedItem feed) {
        Cursor cursor = mContentResolver.query(
                FeedContentProvider.FEEDS_CONTENT_URI,
                new String[]{FeedContentProvider.FEED_ID},
                FeedContentProvider.FEED_LINK + " = ? ",
                new String[]{feed.getLink()},
                null
        );
        if (cursor == null) return -1;
        cursor.moveToPosition(0);
        int feedID = cursor.getInt(cursor.getColumnIndexOrThrow(FeedContentProvider.FEED_ID));
        cursor.close();
        return feedID;
    }

    public static void deleteAllFeedsFromChannel(String channel) {
        Cursor cursor = getAllFeedIDsByChannel(channel);
        cursor.moveToPosition(0);
        int cnt = cursor.getCount();
        if (cnt == 0) {
            cursor.close();
            return;
        }
        String[] allFeeds = new String[cnt];
        String where = "";

        for (int i = 0; i < cnt; i++) {
            where += (i == 0 ? "" : "OR ") + FeedContentProvider.FEED_ID + " = ? ";
            allFeeds[i] = String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(FeedContentProvider.FEED_RSS_CHANNEL_FEED_ID)));
            cursor.moveToNext();
        }

        mContentResolver.delete(
                FeedContentProvider.FEEDS_CONTENT_URI,
                where,
                allFeeds
        );
        mContentResolver.delete(
                FeedContentProvider.FEEDS_RSS_CHANNELS_CONTENT_URI,
                FeedContentProvider.FEED_RSS_CHANNEL_CHANNEL_ID + " = ? ",
                new String[]{String.valueOf(getChannelID(channel))}
        );
        cursor.close();
    }

    public static void deleteAllFeeds() {
        mContentResolver.delete(FeedContentProvider.FEEDS_CONTENT_URI, null, null);
        mContentResolver.delete(FeedContentProvider.FEEDS_RSS_CHANNELS_CONTENT_URI, null, null);
    }

    public static Cursor getAllFeedsFromChannel(String channel) {
        Cursor cursor = getAllFeedIDsByChannel(channel);
        cursor.moveToPosition(0);
        int cnt = cursor.getCount();
        if (cnt == 0) {
            cursor.close();
            cursor = mContentResolver.query(
                    FeedContentProvider.FEEDS_CONTENT_URI,
                    new String[]{
                            FeedContentProvider.FEED_ID,
                            FeedContentProvider.FEED_TITLE,
                            FeedContentProvider.FEED_LINK
                    },
                    "_id=-1", // i want to return empty cursor
                    null,
                    null
            );
            return cursor;
        }
        String[] allFeeds = new String[cnt];
        String where = "";

        for (int i = 0; i < cnt; i++) {
            where += (i == 0 ? "" : "OR ") + FeedContentProvider.FEED_ID + " = ? ";
            allFeeds[i] = String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(FeedContentProvider.FEED_RSS_CHANNEL_FEED_ID)));
            cursor.moveToNext();
        }
        cursor.close();
        cursor = mContentResolver.query(
                FeedContentProvider.FEEDS_CONTENT_URI,
                new String[]{
                        FeedContentProvider.FEED_ID,
                        FeedContentProvider.FEED_TITLE,
                        FeedContentProvider.FEED_LINK
                },
                where,
                allFeeds,
                null
        );
        return cursor;
    }
}
