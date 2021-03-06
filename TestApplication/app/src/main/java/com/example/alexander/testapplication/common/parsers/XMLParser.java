package com.example.alexander.testapplication.common.parsers;

import com.example.alexander.testapplication.model.FeedItem;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.text.ParseException;
import java.util.ArrayList;

public class XMLParser {

    public static ArrayList<FeedItem> getFeedItemsXml(Document data, String rssUrl) throws ParseException, NullPointerException {
        ArrayList<FeedItem> feedItems = new ArrayList<>();
        Element root = data.getDocumentElement();
        int startWithChannelTag = 1;
        Node channel = root.getChildNodes().item(startWithChannelTag);
        if (channel == null) { //start parser for wrong pages html
            int startWithoutChannelTag = 0;
            channel = root.getChildNodes().item(startWithoutChannelTag);
        }
        NodeList items = channel.getChildNodes();
        for (int i = 0; i < items.getLength(); i++) {
            Node child = items.item(i);
            if (child.getNodeName().equalsIgnoreCase("item")) {
                NodeList itemChildNodes = child.getChildNodes();
                FeedItem item = new FeedItem();
                parseItemChildNodes(itemChildNodes, item);
                item.setRssChannelUrl(rssUrl);
                feedItems.add(item);
            }
        }
        return feedItems;
    }

    private static void parseItemChildNodes(NodeList itemChildNodes, FeedItem item) {
        for (int j = 0; j < itemChildNodes.getLength(); j++) {
            Node node = itemChildNodes.item(j);
            String nodeName = node.getNodeName();
            if (nodeName.equalsIgnoreCase("title")) {
                item.setTitle(getPlainText(node.getTextContent()));
            } else if (nodeName.equalsIgnoreCase("enclosure")
                    || nodeName.equalsIgnoreCase("media:thumbnail")
                    || nodeName.equalsIgnoreCase("image")) {
                int startPositionTagForImage = 0;
                String url = node.getAttributes().item(startPositionTagForImage).getTextContent();
                item.setThumbnailUrl(url);
            } else if (nodeName.equalsIgnoreCase("description")) {
                String text = node.getTextContent();
                item.setDescription(getPlainText(text));
                //If the photo was not found in enclosure or media:thumbnail tag
                setThumbnailFromDescriptionTag(text, item);
            } else if (nodeName.equalsIgnoreCase("pubDate")) {
                item.setPubDate(getPlainText(node.getTextContent()));
            } else if (nodeName.equalsIgnoreCase("link")) {
                item.setLink(getPlainText(node.getTextContent()));
            } else if (nodeName.equalsIgnoreCase("author")) {
                item.setAuthor(getPlainText(node.getTextContent()));
            }
        }
        initFeedID(item);
    }

    private static void initFeedID(FeedItem item) {
        item.setFeedID(item.getTitle()
                + item.getDescription()
                + item.getLink()
                + item.getPubDate()
                + item.getAuthor()
                + item.getThumbnailUrl());
    }


    private static String getPlainText(String html) {
        return Jsoup.parse(html).text();
    }

    private static void setThumbnailFromDescriptionTag(String text, FeedItem item) {
        int shiftTag = 5;
        int start = text.indexOf("src=\"") + shiftTag;
        int end = text.indexOf("\"", start);
        String src = null;
        if (start > shiftTag) {
            src = text.substring(start, end);
        }
        item.setThumbnailUrl(src);
    }

}
