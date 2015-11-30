package com.raja.hubbleconnected;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.raja.hubbleconnected.objects.FeedInfoAdapter;
import com.raja.hubbleconnected.rss.RssItem;
import com.raja.hubbleconnected.rss.RssReader;
import com.raja.hubbleconnected.utils.Global;

import java.util.ArrayList;

public class FeedInfoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String mRssUrl = "";
    private ArrayList<RssItem> itemsList;

    private int group1Id = 1;
    int homeId = Menu.FIRST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            mRssUrl = bundle.getString(Global.RSS_URL);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.feedInfoRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        loadData(mRssUrl);

        FeedInfoAdapter.setOnItemClickListener(new FeedInfoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                RssItem feedinfo = itemsList.get(position);
                Intent intent = new Intent(FeedInfoActivity.this, FeedDisplayActivity.class);
                intent.putExtra(Global.RSS_FEED_URL, feedinfo.getDataUrl());
                startActivity(intent);
            }
        });

    }

    private void loadData(String feedUrl) {
        new getData().execute(feedUrl);
    }

    private class getData extends AsyncTask<String, ArrayList<RssItem>, ArrayList<RssItem>> {

        ProgressDialog mProgressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            itemsList = new ArrayList<>();
            mProgressDialog = new ProgressDialog(FeedInfoActivity.this);
            mProgressDialog.setMessage("Loading...");
        }

        @Override
        protected ArrayList<RssItem> doInBackground(String... params) {
            try {
                RssReader reader = new RssReader(params[0]);
                ArrayList<RssItem> items = reader.getItems();
                return items;
            }catch (Exception e) {
                Global.showLog("Exception : "+e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<RssItem> rssItems) {
            super.onPostExecute(rssItems);
            itemsList = rssItems;
            if(mProgressDialog.isShowing())
                mProgressDialog.dismiss();

            if(rssItems != null) {
                Global.showLog("items : " + rssItems.size());
                Global.showLog("item desc : " + rssItems.get(0).getImageUrl());

                mAdapter = new FeedInfoAdapter(FeedInfoActivity.this, itemsList);
                mRecyclerView.setAdapter(mAdapter);
            }
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.refresh_feeds:
                refresh();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refresh() {
        loadData(mRssUrl);
    }
}
