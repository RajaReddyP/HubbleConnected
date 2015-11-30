package com.raja.hubbleconnected;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.raja.hubbleconnected.objects.Feed;
import com.raja.hubbleconnected.objects.FeedAdapter;
import com.raja.hubbleconnected.utils.Global;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Feed> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new FeedAdapter(MainActivity.this, loadData());
        mRecyclerView.setAdapter(mAdapter);

        FeedAdapter.setOnItemClickListener(new FeedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Global.showLog("position : " + position);
                Intent intent = new Intent(MainActivity.this, FeedInfoActivity.class);
                intent.putExtra(Global.RSS_URL, list.get(position).getFeedUrl());
                startActivity(intent);
            }
        });
    }

    private ArrayList<Feed> loadData() {
        list = new ArrayList<>();
        list.add(new Feed("Top News", R.mipmap.topnews, "http://www.moneycontrol.com/rss/MCtopnews.xml"));
        list.add(new Feed("Latest News", R.mipmap.latest_news, "http://www.moneycontrol.com/rss/latestnews.xml"));
        list.add(new Feed("Most Popular", R.mipmap.most_popular, "http://www.moneycontrol.com/rss/mostpopular.xml"));
        list.add(new Feed("Business News", R.mipmap.business_news, "http://www.moneycontrol.com/rss/business.xml"));
        list.add(new Feed("MF Columns", R.mipmap.mf_logo, "http://www.moneycontrol.com/rss/mfcolumns.xml"));
        list.add(new Feed("Economy", R.mipmap.economy, "http://www.moneycontrol.com/rss/economy.xml"));
        list.add(new Feed("Market Reports", R.mipmap.market_report, "http://www.moneycontrol.com/rss/marketreports.xml"));
        list.add(new Feed("Global Markets", R.mipmap.global_markets, "http://www.moneycontrol.com/rss/internationalmarkets.xml"));
        list.add(new Feed("Technical", R.mipmap.technical, "http://www.moneycontrol.com/rss/technicals.xml"));
        list.add(new Feed("Insurance News", R.mipmap.insurance_news, "http://www.moneycontrol.com/rss/insurancenews.xml"));
        return list;
    }
}
