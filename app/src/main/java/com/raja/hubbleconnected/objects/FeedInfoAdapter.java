package com.raja.hubbleconnected.objects;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.raja.hubbleconnected.R;
import com.raja.hubbleconnected.lazyload.ImageLoader;
import com.raja.hubbleconnected.rss.RssItem;
import com.raja.hubbleconnected.utils.Global;

import java.util.ArrayList;

/**
 * Created by Rajareddy on 28/11/15.
 */
public class FeedInfoAdapter extends RecyclerView.Adapter<FeedInfoAdapter.ViewHolder> {

    ImageLoader imageLoader;
    private ArrayList<RssItem> dataList;
    private static OnItemClickListener mOnItemClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtTitle;
        public ImageView imgFeed;

        public ViewHolder(View v) {
            super(v);
            txtTitle = (TextView) v.findViewById(R.id.feedInfoTitle);
            imgFeed = (ImageView) v.findViewById(R.id.feedInfoIcon);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mOnItemClickListener != null)
                mOnItemClickListener.onItemClick(v, getLayoutPosition());
        }
    }

    public FeedInfoAdapter(Context mContext, ArrayList<RssItem> list) {
        imageLoader = new ImageLoader(mContext);
        this.dataList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_info_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RssItem item = dataList.get(position);
        Global.showLog("title  : "+item.getTitle());
        holder.txtTitle.setText(item.getTitle());
        imageLoader.DisplayImage(item.getImageUrl(), holder.imgFeed);
        //holder.imgFeed.setImageResource(R.mipmap.business_news);
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
