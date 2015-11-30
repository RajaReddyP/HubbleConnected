package com.raja.hubbleconnected.objects;

/**
 * Created by Rajareddy on 28/11/15.
 */
public class FeedInfo {
    private String feedTitle;
    private int feedImage;

    public String getFeedUrl() {
        return feedUrl;
    }

    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    private String feedUrl;

    public FeedInfo() {

    }
    public FeedInfo(String title, int image, String feedurl) {
        this.feedTitle = title;
        this.feedUrl = feedurl;
        this.feedImage = image;
    }
    public int getFeedImage() {
        return feedImage;
    }

    public void setFeedImage(int feedImage) {
        this.feedImage = feedImage;
    }

    public String getFeedTitle() {
        return feedTitle;
    }

    public void setFeedTitle(String feedTitle) {
        this.feedTitle = feedTitle;
    }
}
