package com.example.alexander.testapplication.common.parsers;

import com.example.alexander.testapplication.common.RSSHandler;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class XMLParserSax {
    public static void parse(InputStream inputStream, String channel) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            RSSHandler handler = new RSSHandler(channel);
            saxParser.parse(inputStream, handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
