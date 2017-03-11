package com.example.alexander.testapplication.controller;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.example.alexander.testapplication.model.FeedItem;
import com.example.alexander.testapplication.ui.activities.PreviewRssItemActivity_;
import com.example.alexander.testapplication.ui.adapters.RecyclerAdapter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class ReadRss extends AsyncTask<Void, Void, Void> {
    private String mAddress;
    private ArrayList<FeedItem> mFeedItems = new ArrayList<>();
    private URL mUrl;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private Context mContext;

    public ReadRss(Context context, RecyclerView recyclerView, String address, SwipeRefreshLayout refreshLayout) {
        mAddress = address;
        mRecyclerView = recyclerView;
        mRefreshLayout = refreshLayout;
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mRefreshLayout.setRefreshing(false);
        RecyclerAdapter adapter = new RecyclerAdapter((v, position) -> PreviewRssItemActivity_.intent(v.getContext()).
                extra(FeedItem.class.getCanonicalName(), mFeedItems.get(position)).start(), mFeedItems);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected Void doInBackground(Void... params) {
        ProcessXml(Getdata());
        return null;
    }

    private void ProcessXml(Document data) {
        if (data != null) {
            Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(1);
            NodeList items = channel.getChildNodes();
            for (int i = 0; i < items.getLength(); i++) {
                Node cureentchild = items.item(i);
                if (cureentchild.getNodeName().equalsIgnoreCase("item")) {
                    FeedItem item = new FeedItem();
                    NodeList itemchilds = cureentchild.getChildNodes();
                    for (int j = 0; j < itemchilds.getLength(); j++) {
                        Node cureent = itemchilds.item(j);
                        if (cureent.getNodeName().equalsIgnoreCase("title")) {
                            item.setTitle(cureent.getTextContent());
                        } else if (cureent.getNodeName().equalsIgnoreCase("description")) {
                            String text = cureent.getTextContent();
                            item.setDescription(text);

                            int start = text.indexOf("src=\"") + 5;
                            int end = text.indexOf("\"", start);
                            String src = "";
                            if (end != -1 && start > 5) {
                                src = text.substring(start, end);
                            }
                            item.setThumbnailUrl(src);
                        } else if (cureent.getNodeName().equalsIgnoreCase("pubDate")) {
                            item.setPubDate(cureent.getTextContent());
                        } else if (cureent.getNodeName().equalsIgnoreCase("link")) {
                            item.setLink(cureent.getTextContent());
                        } else if (cureent.getNodeName().equalsIgnoreCase("author")) {
                            item.setAuthor(cureent.getTextContent());
                        }
                        if (cureent.getNodeName().equalsIgnoreCase("enclosure")
                                || cureent.getNodeName().equalsIgnoreCase("media:thumbnail")) {
                            //this will return us thumbnail mUrl // enclosure, img(src=), media:thumbnail
                            String url = cureent.getAttributes().item(0).getTextContent();
                            item.setThumbnailUrl(url);
                        }
                    }
                    mFeedItems.add(item);
                }
            }
        }
    }

    public ArrayList<FeedItem> getFeedItems() {
        return mFeedItems;
    }

    private Document Getdata() {
        try {
            mUrl = new URL(mAddress);
            HttpURLConnection connection = (HttpURLConnection) mUrl.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDoc = builder.parse(inputStream);
            return xmlDoc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
