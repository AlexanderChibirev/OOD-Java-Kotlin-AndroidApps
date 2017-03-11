package com.example.alexander.testapplication.common;


import com.example.alexander.testapplication.model.FeedItem;
import com.example.alexander.testapplication.model.dataBase.DataUtils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RSSHandler extends DefaultHandler {
    private int type;

    private boolean inItem;
    private boolean inTitle;
    private boolean inLink;
    private boolean inD;

    private FeedItem feedItem;

    private String curString;
    private boolean saveText = false;

    private int rssChannelID;

    public RSSHandler(String channel) {
        rssChannelID = DataUtils.getChannelID(channel);
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        saveText = false;
        if (qName.equals("item") || qName.equals("entry")) {
            inItem = true;
            feedItem = new FeedItem();
            feedItem.setChannelID(rssChannelID);
            if (qName.equals("item")) {
                type = 1;
            } else {
                type = 2;
            }
        } else if (inItem && qName.equals("title")) {
            inTitle = true;
            curString = "";
            saveText = true;
        } else if (inItem && qName.equals("link")) {
            inLink = true;
            curString = "";
            saveText = true;
            if (type == 2) {
                feedItem.setLink(attributes.getValue("href"));
                saveText = false;
            }
        } else if (inItem && qName.equals("pubDate")) {
            inD = true;
            curString = "";
            saveText = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("item") || qName.equals("entry")) {
            DataUtils.addFeed(feedItem);
            inItem = false;
        } else if (qName.equals("title") && inTitle) {
            curString = curString.trim();
            feedItem.setTitle(curString);
            inTitle = false;
        } else if (qName.equals("link") && type == 1 && inLink) {
            curString = curString.trim();
            feedItem.setLink(curString);
            inLink = false;
        } else if (qName.equals("pubDate") && inD) {
            curString = curString.trim();
            feedItem.setTitle(curString);
            inD = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String tmp = new String(ch, start, length);
        if (saveText) {
            curString += tmp;
        }
    }
}
