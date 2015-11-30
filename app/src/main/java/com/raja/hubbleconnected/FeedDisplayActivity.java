package com.raja.hubbleconnected;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.raja.hubbleconnected.utils.Global;

public class FeedDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String url  = "";
        if(bundle != null) {
            url = bundle.getString(Global.RSS_FEED_URL);
        }
        WebView webview = (WebView) findViewById(R.id.webView);
        webview.setWebViewClient(new WebViewClient() {
            ProgressDialog mProgressDialog;

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if(mProgressDialog == null) {
                    mProgressDialog = new ProgressDialog(FeedDisplayActivity.this);
                    mProgressDialog.setMessage("Loading...");
                    mProgressDialog.show();
                }
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                //Global.showLog("onLoadResource");

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //Global.showLog("onPageFinished");
                try {
                   if(mProgressDialog.isShowing()) {
                       mProgressDialog.dismiss();
                       mProgressDialog = null;
                   }
                }catch (Exception e) {
                    Global.showLog("Exception : "+e);
                }
            }
        });


        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);
    }

}
