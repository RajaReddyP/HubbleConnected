package com.raja.hubbleconnected.rss;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class RssReader {
	
	private String rssUrl;

	public RssReader(String rssUrl) {
		this.rssUrl = rssUrl;
	}

	public ArrayList<RssItem> getItems() throws Exception {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		RssParseHandler handler = new RssParseHandler();
		
		saxParser.parse(rssUrl, handler);

        //Global.showLog("rssurl : "+rssUrl);
		return handler.getItems();
		
	}

}
