package com.example.alexander.testapplication.controller;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.example.alexander.testapplication.common.RSSResultReceiver;
import com.example.alexander.testapplication.common.parsers.XMLParser;
import com.example.alexander.testapplication.model.FeedItem;
import com.example.alexander.testapplication.ui.activities.PreviewRssItemActivity_;
import com.example.alexander.testapplication.ui.adapters.RecyclerAdapter;

import org.w3c.dom.Document;

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
    private Context mContext;
    private final RSSResultReceiver mReceiver;

    public ReadRss(Context context, RecyclerView recyclerView,
                   String address, SwipeRefreshLayout refreshLayout, ArrayList<FeedItem> feedItems) {
        mAddress = address;
        mRecyclerView = recyclerView;
        mSwipeRefreshLayout = refreshLayout;
        mContext = context;
        mReceiver = new RSSResultReceiver(new Handler(), mContext);
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
        RecyclerAdapter adapter = new RecyclerAdapter((v, position) -> PreviewRssItemActivity_.intent(v.getContext()).
                extra(FeedItem.class.getCanonicalName(), mFeedItems.get(position)).start(), mFeedItems);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected Void doInBackground(Void... params) {
        mFeedItems = XMLParser.getFeedItemsXml(getData());
        return null;
    }

    private Document getData() {
        try {
            URL mUrl = new URL(mAddress);
            HttpURLConnection connection = (HttpURLConnection) mUrl.openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = builderFactory.newDocumentBuilder();
                Document xmlDoc = builder.parse(inputStream);
                return xmlDoc;
            } else {
                mReceiver.send(RSSResultReceiver.SERVER_ERROR, null);
                return null;
            }
        } catch (MalformedURLException e) {
            mReceiver.send(RSSResultReceiver.URL_OR_RSS_CHANNEL_ERROR, null);
            e.printStackTrace();
        } catch (UnknownHostException e) {
            mReceiver.send(RSSResultReceiver.NETWORK_CONNECTION_ERROR, null);
        } catch (Exception e) {
            mReceiver.send(RSSResultReceiver.UNKNOWN_ERROR, null);
            e.printStackTrace();
        }
        return null;
    }
}
