package com.raja.hubbleconnected.rss;

public class RssItem {
	
	private String title;
    private String imageUrl;
    private String dataUrl;
    private String date;

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getDataUrl() {
        return dataUrl;
    }
    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;

    }
    public void setTitle(String title) {
        this.title = title;
    }

	@Override
	public String toString() {
		return title;
	}
	
}
