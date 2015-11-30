package com.raja.hubbleconnected.objects;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.raja.hubbleconnected.R;

import java.util.ArrayList;

/**
 * Created by Rajareddy on 28/11/15.
 */
public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Feed> dataList;
    private static OnItemClickListener mOnItemClickListener;
    private int subscribeFeeds = 0;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtTitle;
        public ImageView imgFeed;
        public FrameLayout imgSelect;

        public ViewHolder(View v) {
            super(v);
            txtTitle = (TextView) v.findViewById(R.id.feedTitle);
            imgFeed = (ImageView) v.findViewById(R.id.feedIcon);
            imgSelect = (FrameLayout) v.findViewById(R.id.addFeed);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mOnItemClickListener != null)
                mOnItemClickListener.onItemClick(v, getLayoutPosition());
        }
    }

    public FeedAdapter(Context context, ArrayList<Feed> list) {
        mContext = context;
        this.dataList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Feed feed = dataList.get(position);
        holder.txtTitle.setText(feed.getFeedTitle());
        holder.imgFeed.setImageResource(feed.getFeedImage());
        holder.imgSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscribeFeeds++;
                if(subscribeFeeds > 3) {
                    Toast.makeText(mContext, "Maximum 3 feeds you can subscribe", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext, feed.getFeedTitle()+" feed Subscribed ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
    public static void setOnItemClickListener(OnItemClickListener clickListener) {
        mOnItemClickListener = clickListener;
    }
}
