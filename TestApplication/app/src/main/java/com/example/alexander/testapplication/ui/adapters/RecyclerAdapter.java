package com.example.alexander.testapplication.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexander.testapplication.R;
import com.example.alexander.testapplication.common.CustomItemClickListener;
import com.example.alexander.testapplication.model.FeedItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private CustomItemClickListener mListener;
    private ArrayList<FeedItem> mFeedItems;

    public RecyclerAdapter(CustomItemClickListener listener, ArrayList<FeedItem> feedItems) {
        mListener = listener;
        mFeedItems = feedItems;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemTitle;
        TextView itemDescription;

        ViewHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView) itemView.findViewById(R.id.item_thumbnail);
            itemTitle = (TextView) itemView.findViewById(R.id.item_title);
            itemDescription = (TextView) itemView.findViewById(R.id.item_detail);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rrs_item_model_for_recycler_view, viewGroup, false);
        final int position = i;
        v.setOnClickListener(v1 -> mListener.onItemClick(v1, position));
        return new ViewHolder(v);
    }


    @Override
    public int getItemCount() {
        return mFeedItems.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(mFeedItems.get(i).getTitle());
        viewHolder.itemDescription.setText(mFeedItems.get(i).getDescription());

     /*   new Picasso.Builder(viewHolder.itemImage.getContext())
                .downloader(new OkHttpDownloader(viewHolder.itemImage.getContext(), Integer.MAX_VALUE))
                .build()
                .load("http://i.imgur.com/DvpvklR.png")
                .fit()
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error_fallback)
                .into(viewHolder.itemImage);*/

        Picasso.with(viewHolder.itemImage.getContext())
                .load(mFeedItems.get(i).getThumbnailUrl())
                //.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .fit()
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error_fallback)
                .into(viewHolder.itemImage);
    }
}