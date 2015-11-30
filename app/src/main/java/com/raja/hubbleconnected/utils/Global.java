package com.raja.hubbleconnected.utils;

import android.util.Log;

/**
 * Created by Rajareddy on 28/11/15.
 */
public class Global {

    private final static boolean show = true;
    private final static String TAG = "Rss";

    public static String RSS_URL = "RSS_URL";
    public static String RSS_FEED_URL = "RSS_FEED_URL";

    public static void showLog(String msg) {
        if(show)
            Log.i(TAG, msg);
    }
}
