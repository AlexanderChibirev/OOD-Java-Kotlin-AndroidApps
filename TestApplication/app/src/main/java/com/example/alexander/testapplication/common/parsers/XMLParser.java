package com.example.alexander.testapplication.common.parsers;

import com.example.alexander.testapplication.model.FeedItem;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class XMLParser {

    public static ArrayList<FeedItem> getFeedItemsXml(Document data) {
        ArrayList<FeedItem> feedItems = new ArrayList<>();
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
                            item.setTitle(getPlainText(cureent.getTextContent()));
                        } else if (cureent.getNodeName().equalsIgnoreCase("enclosure")
                                || cureent.getNodeName().equalsIgnoreCase("media:thumbnail")) {
                            String url = cureent.getAttributes().item(0).getTextContent();
                            item.setThumbnailUrl(url);
                        } else if (cureent.getNodeName().equalsIgnoreCase("description")) {
                            String text = cureent.getTextContent();
                            item.setDescription(getPlainText(text));
                            setThumbnailFromDescriptionTag(text, item);
                        } else if (cureent.getNodeName().equalsIgnoreCase("pubDate")) {
                            item.setPubDate(getPlainText(cureent.getTextContent()));
                        } else if (cureent.getNodeName().equalsIgnoreCase("link")) {
                            item.setLink(getPlainText(cureent.getTextContent()));
                        } else if (cureent.getNodeName().equalsIgnoreCase("author")) {
                            item.setAuthor(getPlainText(cureent.getTextContent()));
                        }
                    }
                    feedItems.add(item);
                }
            }
        }
        return feedItems;
    }


    private static String getPlainText(String html) {
        String htmlBody = html.replaceAll("<hr>", "");
        String plainTextBody = htmlBody.replaceAll("<[^<>]+>([^<>]*)<[^<>]+>", "$1");
        plainTextBody = plainTextBody.replaceAll("<br ?/>", "");
        return plainTextBody;
    }

    private static void setThumbnailFromDescriptionTag(String text, FeedItem item) {
        int shiftTag = 5;
        int start = text.indexOf("src=\"") + shiftTag;
        int end = text.indexOf("\"", start);
        String src = "";
        if (start > shiftTag) {
            src = text.substring(start, end);
        }
        item.setThumbnailUrl(src);
    }

}
