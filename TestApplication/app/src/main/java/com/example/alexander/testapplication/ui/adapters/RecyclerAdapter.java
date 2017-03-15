package com.example.alexander.testapplication.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexander.testapplication.R;
import com.example.alexander.testapplication.common.utils.ImageLoaderUtils;
import com.example.alexander.testapplication.controller.AppController;
import com.example.alexander.testapplication.model.FeedItem;

import io.realm.RealmChangeListener;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements RealmChangeListener {

    private CustomItemClickListener mListener;
    private AppController mAppController;

    public RecyclerAdapter(CustomItemClickListener listener, AppController controller) {
        mListener = listener;
        mAppController = controller;
    }

    @Override
    public void onChange(Object element) {
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemTitle;
        TextView itemDescription;
        View view;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            itemImage = (ImageView) itemView.findViewById(R.id.item_thumbnail);
            itemTitle = (TextView) itemView.findViewById(R.id.item_title);
            itemDescription = (TextView) itemView.findViewById(R.id.item_detail);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rrs_item_model_for_recycler_view, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return mAppController.getFeedItems().size();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        FeedItem feedItem = mAppController.getFeedItems().get(position);
        viewHolder.itemTitle.setText(feedItem.getTitle());
        viewHolder.itemDescription.setText(feedItem.getDescription());
        ImageLoaderUtils.downloadImage(
                viewHolder.itemImage.getContext(),
                feedItem.getThumbnailUrl(),
                viewHolder.itemImage);

        viewHolder.view.setOnClickListener(
                view -> mListener.onItemClick(view, feedItem));
    }
}