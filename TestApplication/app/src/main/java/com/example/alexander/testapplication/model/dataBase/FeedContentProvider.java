package com.example.alexander.testapplication.model.dataBase;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.alexander.testapplication.R;

import java.util.HashMap;

public class FeedContentProvider extends ContentProvider {


    private static final String DB_NAME = "DBOfFeeds";
    private static final int DB_VERSION = 1;

    // Feeds table
    private static final String FEEDS_TABLE = "feeds";
    // Feeds item
    public static final String FEED_ID = "_id";
    public static final String FEED_TITLE = "title";
    public static final String FEED_AUTHOR = "author";
    public static final String FEED_LINK = "link";
    public static final String FEED_DESCRIPTION = "description";
    public static final String FEED_PUB_DATE = "pub_date";
    public static final String FEED_THUMBNAIL_URL = "thumbnail_url";

    // Channels
    private static final String RSS_CHANNELS_TABLE = "channels";

    public static final String RSS_CHANNEL_ID = "_id";
    public static final String RSS_CHANNEL_TITLE = "title";

    // Feeds -> Channels
    private static final String FEEDS_RSS_CHANNELS_TABLE = "feeds_channels";

    public static final String FEED_RSS_CHANNEL_ID = "_id";
    public static final String FEED_RSS_CHANNEL_FEED_ID = "feed_id";
    public static final String FEED_RSS_CHANNEL_CHANNEL_ID = "channel_id";

    private static final String FEEDS_TABLE_CREATE = "CREATE TABLE "
            + FEEDS_TABLE + "("
            + FEED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FEED_TITLE + " TEXT NOT NULL, "
            + FEED_LINK + " TEXT NOT NULL);";
    private static final String RSS_CHANNELS_TABLE_CREATE = "CREATE TABLE "
            + RSS_CHANNELS_TABLE + "("
            + RSS_CHANNEL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + RSS_CHANNEL_TITLE + " TEXT NOT NULL);";
    private static final String FEEDS_RSS_CHANNELS_TABLE_CREATE = "CREATE TABLE "
            + FEEDS_RSS_CHANNELS_TABLE + "("
            + FEED_RSS_CHANNEL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FEED_RSS_CHANNEL_FEED_ID + " INTEGER, "
            + FEED_RSS_CHANNEL_CHANNEL_ID + " INTEGER);";


    // Provider constants
    private static final String AUTHORITY = "com.example.alexander.testapplication";
    private static final String FEEDS_PATH = FEEDS_TABLE;
    private static final String RSS_CHANNELS_PATH = RSS_CHANNELS_TABLE;
    private static final String FEEDS_RSS_CHANNELS_PATH = FEEDS_RSS_CHANNELS_TABLE;

    public static final Uri DATA_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY);
    public static final Uri FEEDS_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + FEEDS_PATH);
    public static final Uri RSS_CHANNELS_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + RSS_CHANNELS_PATH);
    public static final Uri FEEDS_RSS_CHANNELS_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + FEEDS_RSS_CHANNELS_PATH);

    private static final int FEEDS = 1;
    private static final int FEEDS_ID = 2;
    private static final int RSS_CHANNELS = 3;
    private static final int RSS_CHANNELS_ID = 4;
    private static final int FEEDS_RSS_CHANNELS = 5;
    private static final int FEEDS_RSS_CHANNELS_ID = 6;

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, FEEDS_PATH, FEEDS);
        uriMatcher.addURI(AUTHORITY, FEEDS_PATH + "/#", FEEDS_ID);
        uriMatcher.addURI(AUTHORITY, RSS_CHANNELS_PATH, RSS_CHANNELS);
        uriMatcher.addURI(AUTHORITY, RSS_CHANNELS_PATH + "/#", RSS_CHANNELS_ID);
        uriMatcher.addURI(AUTHORITY, FEEDS_RSS_CHANNELS_PATH, FEEDS_RSS_CHANNELS);
        uriMatcher.addURI(AUTHORITY, FEEDS_RSS_CHANNELS_PATH + "/#", FEEDS_RSS_CHANNELS_ID);
    }

    private static HashMap<String, String> FEEDS_PROJECTION_MAP;
    private static HashMap<String, String> RSS_CHANNELS_PROJECTION_MAP;
    private static HashMap<String, String> FEEDS_RSS_CHANNELS_PROJECTION_MAP;

    //path segment index for AUTHORITY
    private static int SEGMENT_PATH_INDEX = 1;

    //Help class
    private static class DataBaseHelper extends SQLiteOpenHelper {
        DataBaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(FEEDS_TABLE_CREATE);
            db.execSQL(RSS_CHANNELS_TABLE_CREATE);
            db.execSQL(FEEDS_RSS_CHANNELS_TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS" + FEEDS_TABLE);
            db.execSQL("DROP TABLE IF EXISTS" + RSS_CHANNELS_TABLE);
            db.execSQL("DROP TABLE IF EXISTS" + FEEDS_RSS_CHANNELS_TABLE);
            onCreate(db);
        }
    }

    private Context mContext;
    private SQLiteDatabase mDb;

    @Override
    public boolean onCreate() {
        mContext = getContext();
        DataBaseHelper dbHelper = new DataBaseHelper(mContext);
        mDb = dbHelper.getWritableDatabase();
        return mDb != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection,
                        String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        switch (uriMatcher.match(uri)) {
            case FEEDS:
                qb.setTables(FEEDS_TABLE);
                qb.setProjectionMap(FEEDS_PROJECTION_MAP);
                break;
            case FEEDS_ID:
                qb.setTables(FEEDS_TABLE);
                qb.appendWhere(FEED_ID + "=" + uri.getPathSegments().get(SEGMENT_PATH_INDEX));
                break;
            case RSS_CHANNELS:
                qb.setTables(RSS_CHANNELS_TABLE);
                qb.setProjectionMap(RSS_CHANNELS_PROJECTION_MAP);
                break;
            case RSS_CHANNELS_ID:
                qb.setTables(RSS_CHANNELS_TABLE);
                qb.appendWhere(RSS_CHANNEL_ID +
                        "=" + uri.getPathSegments().get(SEGMENT_PATH_INDEX));
                break;
            case FEEDS_RSS_CHANNELS:
                qb.setTables(FEEDS_RSS_CHANNELS_TABLE);
                qb.setProjectionMap(FEEDS_RSS_CHANNELS_PROJECTION_MAP);
                break;
            case FEEDS_RSS_CHANNELS_ID:
                qb.setTables(FEEDS_RSS_CHANNELS_TABLE);
                qb.appendWhere(FEED_RSS_CHANNEL_ID +
                        "=" + uri.getPathSegments().get(SEGMENT_PATH_INDEX));
                break;
            default:
                throw new IllegalArgumentException(mContext.getString(R.string.unknown_uri) + uri);
        }
        Cursor c = qb.query(mDb, projection, selection, selectionArgs,
                null, null, sortOrder);
        c.setNotificationUri(mContext.getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case FEEDS:
                return "vnd.android.cursor.dir/vnd."
                        + mContext.getString(R.string.app_name)
                        + ".feeds";
            case FEEDS_ID:
                return "vnd.android.cursor.item/vnd."
                        + mContext.getString(R.string.app_name)
                        + ".feeds";
            case RSS_CHANNELS:
                return "vnd.android.cursor.dir/vnd."
                        + mContext.getString(R.string.app_name)
                        + ".channels";
            case RSS_CHANNELS_ID:
                return "vnd.android.cursor.item/vnd."
                        + mContext.getString(R.string.app_name)
                        + ".channels";
            case FEEDS_RSS_CHANNELS:
                return "vnd.android.cursor.dir/vnd."
                        + mContext.getString(R.string.app_name)
                        + ".feeds_channels";
            case FEEDS_RSS_CHANNELS_ID:
                return "vnd.android.cursor.item/vnd."
                        + mContext.getString(R.string.app_name)
                        + ".feeds_channels";
            default:
                throw new IllegalArgumentException(mContext.getString(R.string.unknown_uri) + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        switch (uriMatcher.match(uri)) {
            case FEEDS:
                return getUriFeeds(uri, contentValues);
            case RSS_CHANNELS:
                return getUriRssChannel(uri, contentValues);
            case FEEDS_RSS_CHANNELS:
                return getUriFeedsRssChannel(uri, contentValues);
            default:
                throw new IllegalArgumentException(mContext.getString(R.string.unknown_uri) + uri);
        }
    }

    private Uri getUriFeeds(Uri uri, ContentValues contentValues) {
        long rowID = mDb.insert(FEEDS_TABLE, "", contentValues);
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(FEEDS_CONTENT_URI, rowID);
            mContext.getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException(mContext.getString(R.string.feed_inserting_error) + uri);
    }

    private Uri getUriRssChannel(Uri uri, ContentValues contentValues) {
        long rowID = mDb.insert(RSS_CHANNELS_TABLE, "", contentValues);
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(RSS_CHANNELS_CONTENT_URI, rowID);
            mContext.getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException(mContext.getString(R.string.rss_inserting_error) + uri);
    }

    private Uri getUriFeedsRssChannel(Uri uri, ContentValues contentValues) {
        long rowID = mDb.insert(FEEDS_RSS_CHANNELS_TABLE, "", contentValues);
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(FEEDS_RSS_CHANNELS_CONTENT_URI, rowID);
            mContext.getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException(mContext.getString(R.string.feed_and_rss_binding_error) + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        int count;
        switch (uriMatcher.match(uri)) {
            case FEEDS:
                count = mDb.delete(FEEDS_TABLE, selection, selectionArgs);
                break;
            case FEEDS_ID:
                String id = uri.getPathSegments().get(SEGMENT_PATH_INDEX);
                count = mDb.delete(FEEDS_TABLE, FEED_ID + " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" +
                                selection + ")" : ""), selectionArgs);
                break;
            case RSS_CHANNELS:
                count = mDb.delete(RSS_CHANNELS_TABLE, selection, selectionArgs);
                break;
            case RSS_CHANNELS_ID:
                id = uri.getPathSegments().get(SEGMENT_PATH_INDEX);
                count = mDb.delete(RSS_CHANNELS_TABLE, RSS_CHANNEL_ID + " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" +
                                selection + ")" : ""), selectionArgs);
                break;
            case FEEDS_RSS_CHANNELS:
                count = mDb.delete(FEEDS_RSS_CHANNELS_TABLE, selection, selectionArgs);
                break;
            case FEEDS_RSS_CHANNELS_ID:
                id = uri.getPathSegments().get(SEGMENT_PATH_INDEX);
                count = mDb.delete(FEEDS_RSS_CHANNELS_TABLE, FEED_RSS_CHANNEL_ID + " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" +
                                selection + ")" : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException(mContext.getString(R.string.unknown_uri) + uri);
        }
        mContext.getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values,
                      String selection, String[] selectionArgs) {
        int count;
        switch (uriMatcher.match(uri)) {
            case FEEDS:
                count = mDb.update(FEEDS_TABLE, values,
                        selection, selectionArgs);
                break;
            case FEEDS_ID:
                String id = uri.getPathSegments().get(SEGMENT_PATH_INDEX);
                count = mDb.update(FEEDS_TABLE, values, FEED_ID +
                        " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" +
                                selection + ")" : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException(mContext.getString(R.string.unknown_uri) + uri);
        }
        mContext.getContentResolver().notifyChange(uri, null);
        return count;
    }
}
