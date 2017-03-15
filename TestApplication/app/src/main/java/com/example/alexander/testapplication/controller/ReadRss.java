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
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class ReadRss extends AsyncTask<Void, Void, Void> {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private final RSSResultReceiver mReceiver;
    private AppController mAppController;

    public ReadRss(Context context, RecyclerView recyclerView,
                   SwipeRefreshLayout refreshLayout, AppController appController) {
        mAppController = appController;
        mRecyclerView = recyclerView;
        mSwipeRefreshLayout = refreshLayout;
        mReceiver = new RSSResultReceiver(new Handler(), context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mAppController.UpdateDataFromDB();
        RecyclerAdapter adapter = new RecyclerAdapter((view, feedItem) ->
                PreviewRssItemActivity_.intent(
                        view.getContext()).
                        extra(FeedItem.class.getCanonicalName(), feedItem).start(),
                mAppController);
        mRecyclerView.setAdapter(adapter);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Document document = getData();
            mAppController.setFeedItems(XMLParser.getFeedItemsXml(document, mAppController.getRssUrl()));
        } catch (MalformedURLException e) {
            mReceiver.send(RSSResultReceiver.URL_OR_RSS_CHANNEL_ERROR, null);
        } catch (UnknownHostException | FileNotFoundException e) {
            mReceiver.send(RSSResultReceiver.UNKNOWN_HOST, null);
        } catch (Exception e) {
            mReceiver.send(RSSResultReceiver.UNKNOWN_ERROR, null);
        }
        return null;
    }

    private Document getData() throws IOException, ParserConfigurationException, SAXException {
        URL url = new URL(mAppController.getRssUrl());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = connection.getInputStream();
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        return builder.parse(inputStream);
    }
}
