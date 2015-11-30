package com.raja.hubbleconnected.rss;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class RssParseHandler extends DefaultHandler {

	private ArrayList<RssItem> rssItems;
	
	private RssItem currentItem;
    private boolean isItem = false;
	private boolean isTitle = false;
    private boolean isLink = false;
	private boolean isDescription =false;
    private boolean isDate = false;
    private StringBuffer mSb;

	public RssParseHandler() {
		rssItems = new ArrayList<RssItem>();
	}
	
	public ArrayList<RssItem> getItems() {
		return rssItems;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //Global.showLog( "qName : " + qName+", local name: "+localName+  ", uri : "+uri+", attributes : "+attributes);
        mSb = new StringBuffer();
        String value = localName.trim();
        if (value.equalsIgnoreCase("item")) {
			currentItem = new RssItem();
            isItem = true;
		} else if (value.equalsIgnoreCase("title")) {
            isTitle = true;
		} else if (value.equalsIgnoreCase("link")) {
            isLink = true;
		}else if (value.equalsIgnoreCase("pubDate")) {
            isDate = true;
		}else if (value.equalsIgnoreCase("description")) {
            isDescription = true;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
        //Global.showLog("uri : " + uri + ", localName : " + localName);
        String imagesUrl = null;
        String value = localName.trim();
		if (value.equalsIgnoreCase("item")) {
			rssItems.add(currentItem);
			currentItem = null;
		} else if (value.equalsIgnoreCase("title")) {
            isTitle = false;
		} else if (value.equalsIgnoreCase("link")) {
            isLink = false;
		}else if (value.equalsIgnoreCase("pubDate")) {
            isDate = false;
		}else if (value.equalsIgnoreCase("description")) {
            if(isItem) {
                //Global.showLog("has description");
                String str = mSb.toString().trim();
                String[] temp;
                //Global.showLog("has description : " + str);
                if (str.contains("<img src=")) {
                    //Global.showLog("has description : " + str);
                    temp = str.split("<img src=");
                    //Global.showLog("temp : " + temp);
                    if (temp == null) {

                    } else {
                        //Global.showLog("temp[1] " + temp[1]);
                        imagesUrl = temp[1].substring(temp[1].indexOf("\""), temp[1].indexOf(" "));
                        imagesUrl = imagesUrl.replace("\"", "");
                        //Global.showLog("image url : " + imagesUrl);
                        currentItem.setImageUrl(imagesUrl);
                    }

                } else {
                    currentItem.setImageUrl("");
                }
                //Global.showLog("image url : " + imagesUrl);
                //Log.i("des ",removeContentSpanObjects(mSb).toString().trim() + System.getProperty("line.separator" ) );
                //mItem.setContent(Html.fromHtml(mSb.toString().trim()).toString());
                //mItem.setContent(removeContentSpanObjects(mSb).toString().trim() + System.getProperty("line.separator" ));
                isDescription = false;
            }
            isItem = false;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
        //Global.showLog("ch : "+ch+", start : "+start);
        if(isTitle || isLink || isDescription || isDate) {
            mSb.append(new String(ch, start, length));
        }
		if (isTitle) {
			if (currentItem != null) {
                String title = new String(ch, start, length);
                if(title.length()>0 && !title.equals(""))
                currentItem.setTitle(new String(ch, start, length));
                isTitle = false;
            }
		} else if (isDate) {
			if (currentItem != null) {
				currentItem.setDate(new String(ch, start, length));
                isDate = false;
			}
		}
		else if (isLink) {
			if (currentItem != null) {
				currentItem.setDataUrl(new String(ch, start, length));
                isLink = false;
			}
		}else if (isLink) {
			if (currentItem != null) {
				currentItem.setImageUrl(new String(ch, start, length));
                isLink = false;
			}
		}

	}
	
}
