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
        Element root = data.getDocumentElement();
        Node channel = root.getChildNodes().item(1);
        NodeList items = channel.getChildNodes();
        for (int i = 0; i < items.getLength(); i++) {
            Node cureentChild = items.item(i);
            if (cureentChild.getNodeName().equalsIgnoreCase("item")) {
                NodeList itemChildNodes = cureentChild.getChildNodes();
                FeedItem item = new FeedItem();
                parseItemChildNodes(itemChildNodes, item);
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
                    || nodeName.equalsIgnoreCase("media:thumbnail")) {
                String url = node.getAttributes().item(0).getTextContent();
                item.setThumbnailUrl(url);
            } else if (nodeName.equalsIgnoreCase("description")) {
                String text = node.getTextContent();
                item.setDescription(getPlainText(text));
                setThumbnailFromDescriptionTag(text, item);
            } else if (nodeName.equalsIgnoreCase("pubDate")) {
                item.setPubDate(getPlainText(node.getTextContent()));
            } else if (nodeName.equalsIgnoreCase("link")) {
                item.setLink(getPlainText(node.getTextContent()));
            } else if (nodeName.equalsIgnoreCase("author")) {
                item.setAuthor(getPlainText(node.getTextContent()));
            }
        }
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
        String src = null;
        if (start > shiftTag) {
            src = text.substring(start, end);
        }
        item.setThumbnailUrl(src);
    }

}
