package com.example.hoangphuong.reycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hoang Phuong on 4/11/2017.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.CustomViewHolder> {
    private List<FeedItem> feedItems;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public MyRecyclerViewAdapter(MainActivity context, List<FeedItem> feedItems) {
        this.feedItems = feedItems;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,null);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final FeedItem feedItem = feedItems.get(position);

        if (!TextUtils.isEmpty(feedItem.getTitle())){
            Picasso.with(context).load(feedItem.getThumbnail())
                                    .error(R.drawable.placeholder)
                                    .placeholder(R.drawable.placeholder)
                                    .into(holder.imageView);
        }
        holder.textView.setText(Html.fromHtml(feedItem.getTitle()));
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(feedItem);
            }
        };
        holder.imageView.setOnClickListener(listener);
        holder.textView.setOnClickListener(listener);



    }

    @Override
    public int getItemCount() {
        if (null != feedItems) return feedItems.size();
        else return 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView textView;

        public CustomViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.thumbnail);
            this.textView = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
