package com.example.alexander.testapplication.controller;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.example.alexander.testapplication.common.parsers.RSSResultReceiver;
import com.example.alexander.testapplication.common.parsers.XMLParser;
import com.example.alexander.testapplication.model.FeedItem;
import com.example.alexander.testapplication.ui.activities.PreviewRssItemActivity_;
import com.example.alexander.testapplication.ui.adapters.RecyclerAdapter;

import org.w3c.dom.Document;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class ReadRss extends AsyncTask<Void, Void, Void> {
    private String mAddress;
    private ArrayList<FeedItem> mFeedItems;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private final RSSResultReceiver mReceiver;

    public ReadRss(Context context, RecyclerView recyclerView,
                   String address, SwipeRefreshLayout refreshLayout, ArrayList<FeedItem> feedItems) {
        mAddress = address;
        mRecyclerView = recyclerView;
        mSwipeRefreshLayout = refreshLayout;
        mReceiver = new RSSResultReceiver(new Handler(), context);
        mFeedItems = feedItems;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mSwipeRefreshLayout.setRefreshing(false);
        RecyclerAdapter adapter = new RecyclerAdapter((view, feedItem) -> //set click listener
                PreviewRssItemActivity_.intent(view.getContext()).//transfer data to previewActivity
                        extra(FeedItem.class.getCanonicalName(), feedItem).start(), mFeedItems);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected Void doInBackground(Void... params) {
        Document document = getData();
        if (document != null) {
            mFeedItems = XMLParser.getFeedItemsXml(document);
        }
        return null;
    }

    private Document getData() {
        try {
            URL mUrl = new URL(mAddress);
            HttpURLConnection connection = (HttpURLConnection) mUrl.openConnection();
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            return builder.parse(inputStream);
        } catch (MalformedURLException e) {
            mReceiver.send(RSSResultReceiver.URL_OR_RSS_CHANNEL_ERROR, null);
            e.printStackTrace();
        } catch (UnknownHostException | FileNotFoundException e) {
            mReceiver.send(RSSResultReceiver.UNKNOWN_HOST, null);
        } catch (Exception e) {
            mReceiver.send(RSSResultReceiver.UNKNOWN_ERROR, null);
            e.printStackTrace();
        }
        return null;
    }
}
